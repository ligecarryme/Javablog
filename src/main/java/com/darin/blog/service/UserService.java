package com.darin.blog.service;

import com.darin.blog.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
