package com.service;

import com.model.dto.LoginResponse;
import com.model.dto.UserResponse;
import com.model.request.LoginRequest;
import com.model.request.PasswordChangeRequest;
import com.model.request.UserRequest;

public interface UserService {

    UserResponse registerUser(UserRequest userRequest);

    LoginResponse authorize(LoginRequest request);

    Boolean activateRegistration(String key);

    Boolean changePassword(String tokenRequest, PasswordChangeRequest passwordChangeRequest);

    Boolean joinRoom(String token, Integer roomId);
}
