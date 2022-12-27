package com.semcolon.userManagementSystem.controller;

import com.semcolon.userManagementSystem.dto.requests.CreateUserRequest;
import com.semcolon.userManagementSystem.dto.requests.LoginRequest;
import com.semcolon.userManagementSystem.dto.response.CreateUserResponse;
import com.semcolon.userManagementSystem.dto.response.LoginResponse;
import com.semcolon.userManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CreateUserResponse register( @RequestBody CreateUserRequest createUserRequest){
        return userService.createUserProfile(createUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
