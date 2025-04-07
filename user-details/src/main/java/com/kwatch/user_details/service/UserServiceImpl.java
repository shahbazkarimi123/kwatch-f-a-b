package com.kwatch.user_details.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kwatch.user_details.dao.UserDao;
import com.kwatch.user_details.userDetails.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
     // Constructor injection is preferred
    public UserServiceImpl( UserDao userDao,PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userDao.getUserByUserName(userName);
    }

    @Override
    public User getUserById(Long id) {
        return this.userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void createUser(User userDetails) {
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        this.userDao.createUser(userDetails);
    }

    @Override
    @Transactional
    public void updateUser(User userDetails) {
        this.userDao.updateUser(userDetails);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public String deleteUserById(Long userId) {
        return userDao.deleteUserById(userId);
    }

    

    
    


    
}
