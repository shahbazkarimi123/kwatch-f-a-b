package com.kwatch.user_details.service;

import java.util.List;

import com.kwatch.user_details.userDetails.User;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUserName(String userName);

    User getUserByEmail(String email);

    User getUserById(Long id);

    void createUser(User userDetails);

    void updateUser(User userDetails);
    
    String deleteUserById(Long userId);

}
