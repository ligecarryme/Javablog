package com.darin.blog.service.Impl;

import com.darin.blog.dao.UserRepository;
import com.darin.blog.entity.User;
import com.darin.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
