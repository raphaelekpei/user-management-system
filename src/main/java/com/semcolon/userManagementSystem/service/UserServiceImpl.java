package com.semcolon.userManagementSystem.service;

import com.semcolon.userManagementSystem.data.model.User;
import com.semcolon.userManagementSystem.data.repository.UserRepository;
import com.semcolon.userManagementSystem.dto.requests.CreateUserRequest;
import com.semcolon.userManagementSystem.dto.requests.LoginRequest;
import com.semcolon.userManagementSystem.dto.response.CreateUserResponse;
import com.semcolon.userManagementSystem.dto.response.LoginResponse;
import com.semcolon.userManagementSystem.exceptions.UserManagementException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private int counter;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    To create/save user, you have to invoke the userRepository, and check the user email,
     */
    @Override
    public CreateUserResponse createUserProfile(CreateUserRequest createUserRequest) {
        User user = userRepository.findUserByEmail(createUserRequest.getEmail());
        if(user != null){
            throw new UserManagementException("user with email "+createUserRequest.getEmail()+" already exist");
        }
        user = new User(createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getEmail(),
                createUserRequest.getPhoneNumber(), createUserRequest.getPassword());
        counter = counter + 1;
        user.setId(String.valueOf(counter));
        userRepository.save(user);
       CreateUserResponse createUserResponse = new CreateUserResponse();
       createUserResponse.setMessage("user with email "+createUserRequest.getEmail()+" has been successfully saved" );
       return createUserResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        if(user == null){
            throw new UserManagementException("email or password incorrect!");
        }
        if(user.getPassword().equals(loginRequest.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage("You have successfully logged in!");
            return loginResponse;
        }
        throw new UserManagementException("email or password incorrect!");
    }
}
