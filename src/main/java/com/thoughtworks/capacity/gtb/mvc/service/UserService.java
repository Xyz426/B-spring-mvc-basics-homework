package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    static Map<String,User> userMap = new HashMap<>();

    public void addUser(User user){
        user.setId(userMap.size()+1);
        userMap.put(user.getUsername(),user);
    }

    public boolean hasUser(User user){
        if(userMap.containsKey(user.getUsername())){
            return true;
        }
        return false;
    }
}
