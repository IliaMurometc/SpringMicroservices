package com.example.demo.userservice.impl;

import com.example.demo.shared.Utils;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import com.example.demo.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest userRest = new UserRest();

        String userId = new Utils().generateUserId();
        userRest.setUserId(userId);
        userRest.setFirstName(userDetails.getFirstName());
        userRest.setSecondName(userDetails.getSecondName());
        userRest.setEmail(userDetails.getEmail());

        if (users == null){
            users = new HashMap<>();
        }

        users.put(userId, userRest);

        return userRest;
    }
}
