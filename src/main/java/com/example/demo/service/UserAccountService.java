package com.example.demo.service;

import com.example.demo.model.UserAccount;

public interface UserService {

    UserAccount register(UserAccount user);

    String login(String username, String password);
}
