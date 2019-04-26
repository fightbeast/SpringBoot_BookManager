package com.example.BookManager.service;

import com.example.BookManager.model.constants.User;
import com.example.BookManager.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser(){
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user){
        ConcurrentUtils.setHost(user);
    }
}
