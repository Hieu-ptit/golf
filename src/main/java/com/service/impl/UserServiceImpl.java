package com.service.impl;

import com.domain.Account;
import com.domain.RoleAccount;
import com.domain.Room;
import com.domain.embedded.PlayerObject;
import com.exception.BusinessException;
import com.mapper.UserMapper;
import com.model.bo.RoomStatus;
import com.model.bo.StatusCommon;
import com.model.dto.AccountAuth;
import com.model.dto.DetailUser;
import com.model.dto.LoginResponse;
import com.model.dto.UserResponse;
import com.model.request.LoginRequest;
import com.model.request.PasswordChangeRequest;
import com.model.request.UserRequest;
import com.repository.RoomRepository;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import com.security.AuthoritiesConstants;
import com.security.jwt.TokenProvider;
import com.service.UserService;
import com.util.ErrorCode;
import com.util.ErrorMessage;
import com.util.Global;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tech.jhipster.security.RandomUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoomRepository roomRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final UserRoleRepository userRoleRepository;

    private final MailService mailService;

    private static boolean isPasswordLengthInvalid(String password) {
        return (StringUtils.isEmpty(password) || password.length() < Global.PASSWORD_MIN_LENGTH || password.length() > Global.PASSWORD_MAX_LENGTH);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse registerUser(UserRequest userRequest) {

        if (isPasswordLengthInvalid(userRequest.getPassword()) || !Objects.equals(userRequest.getPassword(), userRequest.getConfirmPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD_TYPE, ErrorMessage.INVALID_PASSWORD_TYPE);
        }
        Account newUser = userRepository.findByEmailAndStatusNot(userRequest.getEmail(), StatusCommon.DELETE).orElse(null);
        if (newUser != null)
            throw new BusinessException(ErrorCode.EMAIL_ALREADY, ErrorMessage.EMAIL_ALREADY);
        else newUser = new Account();
        newUser = userMapper.mapToEntity(newUser, userRequest);
        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setStatus(StatusCommon.INACTIVE);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        userRepository.insert(newUser);
        String userId = newUser.getId();
        RoleAccount userRoleList = userMapper.mapToEntityUserRole(userId);
        userRoleRepository.insert(userRoleList);
        Set<String> roleName = Set.of(AuthoritiesConstants.USER);
        UserResponse userResponse = userMapper.mapToResponse(newUser);
        userResponse.setRoleNames(roleName);
        mailService.sendActivationEmail(newUser);
        return userResponse;
    }

    @Override
    public LoginResponse authorize(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            request.getEmail().toLowerCase(Locale.ENGLISH),
            request.getPassword()
        );
        Set<String> roleList = new HashSet<>();
        Account user = userRepository.findByEmailAndStatusNot(request.getEmail().toLowerCase(Locale.ENGLISH), StatusCommon.DELETE).orElse(null);
        if (user == null)
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, ErrorMessage.USER_NOT_FOUND);
        boolean validatePassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!validatePassword)
            throw new BusinessException(ErrorCode.PASSWORD_INVALID, ErrorMessage.PASS_WORD_INVALID);
        List<RoleAccount> userRoles = userRoleRepository.findByAccountId(user.getId());
        if (!CollectionUtils.isEmpty(userRoles))
            roleList = userRoles.stream().map(RoleAccount::getRoleName).collect(Collectors.toSet());
        AccountAuth accountAuth = userMapper.mapAccountAuth(user);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, request.isRememberMe(), null);

        return new LoginResponse(accountAuth, jwt, roleList);
    }

    public Boolean activateRegistration(String key) {
        Account user = userRepository
            .findOneByActivationKey(key);
        if (user == null)
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, ErrorMessage.USER_NOT_FOUND);
        user.setStatus(StatusCommon.ACTIVE);
        user.setActivationKey(null);
        userRepository.update(user);
        return true;
    }

    public Optional<Account> completePasswordReset(String newPassword, String key) {
        return userRepository
            .findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minus(1, ChronoUnit.DAYS)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
            });
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean changePassword(String tokenRequest, PasswordChangeRequest passwordChangeRequest) {
        if (isPasswordLengthInvalid(passwordChangeRequest.getNewPassword()))
            throw new BusinessException(ErrorCode.PASSWORD_IN_VALID, ErrorMessage.PASS_WORD_INVALID);
        String token = null;
        if (org.springframework.util.StringUtils.hasText(tokenRequest) && tokenRequest.startsWith("Bearer "))
            token = tokenRequest.substring(7);
        DetailUser detailUser = tokenProvider.getClaim(token);
        Account user = userRepository.getById(detailUser.getUserId());
        String currentEncryptedPassword = user.getPassword();
        if (!passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(), currentEncryptedPassword))
            throw new BusinessException(ErrorCode.PASSWORD_IN_VALID, ErrorMessage.PASS_WORD_INVALID);
        if (!passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getRepeatPassword()))
            throw new BusinessException(ErrorCode.PASSWORD_NEW_WRONG, ErrorCode.PASSWORD_NEW_WRONG.getMessage());
        String encryptedPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        userRepository.update(user);
        mailService.sendPasswordResetMail(user);
        return true;
    }

    @Override
    public Boolean joinRoom(String token, Integer roomId) {
        Room room = roomRepository.findByIdAndStatus(roomId, RoomStatus.WAITING).orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOT_FOUND, ErrorMessage.ROOM_NOT_FOUND));
        DetailUser detailUser = getToken(token);
        PlayerObject playerRequest = new PlayerObject().setId(detailUser.getUserId()).setName(detailUser.getName());
        room.getPlayer().add(playerRequest);
        roomRepository.update(room);
        return true;
    }

    private DetailUser getToken(String tokenRequest){
        String token = null;
        if (org.springframework.util.StringUtils.hasText(tokenRequest) && tokenRequest.startsWith("Bearer "))
            token = tokenRequest.substring(7);
        return tokenProvider.getClaim(token);
    }
}
