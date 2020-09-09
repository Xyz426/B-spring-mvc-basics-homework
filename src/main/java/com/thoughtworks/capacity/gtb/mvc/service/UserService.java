package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    static List<User> userList = new ArrayList<>();

    public void addUser(User user){
        user.setId(userList.size()+1);
        userList.add(user);
    }
}
