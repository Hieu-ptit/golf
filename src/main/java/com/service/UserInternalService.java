package com.service;

import com.model.dto.DetailUser;

public interface UserInternalService {

    DetailUser getUserByEmail(String email);
}
