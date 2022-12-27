package com.semcolon.userManagementSystem.data.repository;


import com.semcolon.userManagementSystem.data.model.User;

import java.util.List;

public interface

UserRepository {
    User save(User user);

    User findUserByPhoneNumber(String phoneNumber);
    User findUserById(String id);
    User findUserByEmail(String email);

    // All users would be in a list.
    List<User> findAllUsers();

    User updateUser(String id, User user);
    void deleteUser(String id);

    int size();
}