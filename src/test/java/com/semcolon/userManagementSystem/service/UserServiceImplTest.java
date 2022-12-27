package com.semcolon.userManagementSystem.service;

import com.semcolon.userManagementSystem.data.repository.UserRepository;
import com.semcolon.userManagementSystem.data.repository.UserRepositoryImpl;
import com.semcolon.userManagementSystem.dto.requests.CreateUserRequest;
import com.semcolon.userManagementSystem.dto.requests.LoginRequest;
import com.semcolon.userManagementSystem.dto.response.CreateUserResponse;
import com.semcolon.userManagementSystem.dto.response.LoginResponse;
import com.semcolon.userManagementSystem.exceptions.UserManagementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;
    private CreateUserRequest createUserRequest;
    private CreateUserRequest createUserRequest2;
    private LoginRequest loginRequest;


    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);

        createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Racheal");
        createUserRequest.setLastName("Owolabi");
        createUserRequest.setEmail("racheal@gmail.com");
        createUserRequest.setPhoneNumber("09023354575");
        createUserRequest.setPassword("rac1234");

        createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setFirstName("Bolu");
        createUserRequest2.setLastName("Kassy");
        createUserRequest2.setEmail("racheal@gmail.com");
        createUserRequest2.setPhoneNumber("0900987654");
        createUserRequest2.setPassword("yuio90");

        loginRequest = new LoginRequest();
        loginRequest.setPassword("rac1234");
        loginRequest.setEmail("racheal@gmail.com");
    }

    @Test
    public void userProfileCanBeCreatedTest(){
        CreateUserResponse createUserResponse = userService.createUserProfile(createUserRequest);
        assertNotNull(createUserResponse);
        assertEquals(1, userRepository.size());
        assertEquals("user with email racheal@gmail.com has been successfully saved", createUserResponse.getMessage());
    }
    @Test
    public void sameEmailCannotRegisterTwiceTest(){
        CreateUserResponse createUserResponse = userService.createUserProfile(createUserRequest);
        assertNotNull(createUserResponse);
        assertEquals(1, userRepository.size());
        assertThrows(UserManagementException.class, ()->userService.createUserProfile(createUserRequest2));
    }
    @Test
    public void loginTest(){
        CreateUserResponse createUserResponse = userService.createUserProfile(createUserRequest);
        assertNotNull(createUserResponse);
        assertEquals(1, userRepository.size());
        LoginResponse loginResponse = userService.login(loginRequest);
        assertNotNull(loginResponse);
        assertEquals("You have successfully logged in!",loginResponse.getMessage());

    }

}