package com.nzo.dao;


import com.nzo.entry.User;

public interface UserDao {
    public User findUser(String username);
}
