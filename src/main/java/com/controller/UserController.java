package com.controller;

import com.domain.RoleAccount;
import com.model.dto.LoginResponse;
import com.model.dto.Response;
import com.model.dto.UserResponse;
import com.model.request.LoginRequest;
import com.model.request.PasswordChangeRequest;
import com.model.request.UserRequest;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Response<UserResponse> registerAccount(@Valid @RequestBody UserRequest userRequest) {
        return Response.ofSucceeded(userService.registerUser(userRequest));
    }

    @PostMapping("/authenticate")
    public Response<LoginResponse> authorize(@Valid @RequestBody LoginRequest request) {
        return Response.ofSucceeded(userService.authorize(request));
    }

    @PostMapping("/join-room")
    public Response<Boolean> joinRoom(@RequestHeader("Authorization") String token, @RequestParam(value = "room-id") Integer roomId) {
        return Response.ofSucceeded(userService.joinRoom(token, roomId));
    }

    @GetMapping("/activate")
    public Response<Boolean> activateAccount(@RequestParam(value = "key") String key) {
        return Response.ofSucceeded(userService.activateRegistration(key));
    }

    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    @PostMapping(path = "/change-password")
    public Response<Boolean> changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return Response.ofSucceeded(userService.changePassword(token, passwordChangeRequest));
    }
}
