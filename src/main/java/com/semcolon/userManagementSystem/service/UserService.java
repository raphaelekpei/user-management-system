package com.semcolon.userManagementSystem.service;

import com.semcolon.userManagementSystem.dto.requests.CreateUserRequest;
import com.semcolon.userManagementSystem.dto.requests.LoginRequest;
import com.semcolon.userManagementSystem.dto.response.CreateUserResponse;
import com.semcolon.userManagementSystem.dto.response.LoginResponse;

public interface UserService {
   CreateUserResponse createUserProfile(CreateUserRequest createUserRequest);
   LoginResponse login(LoginRequest loginRequest);


}


