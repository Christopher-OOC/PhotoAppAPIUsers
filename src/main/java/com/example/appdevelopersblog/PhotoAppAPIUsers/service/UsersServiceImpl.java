package com.example.appdevelopersblog.PhotoAppAPIUsers.service;

import com.example.appdevelopersblog.PhotoAppAPIUsers.shared.UserDto;

import java.util.UUID;

public class UsersServiceImpl implements UsersService {


    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}