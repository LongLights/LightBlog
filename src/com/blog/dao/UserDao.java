package com.blog.dao;

import com.blog.entity.User;

public interface UserDao {
    int reg(User user);
    User login(String username,String password);
}
