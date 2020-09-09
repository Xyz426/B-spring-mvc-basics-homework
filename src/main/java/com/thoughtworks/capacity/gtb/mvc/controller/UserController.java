package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.exception.UserIsExistException;
import com.thoughtworks.capacity.gtb.mvc.model.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid User user){
        if(userService.hasUser(user)){
            throw new UserIsExistException("用户已存在");
        }
        System.out.println(1);
        userService.addUser(user);
    }

    @GetMapping("login")
    public ResponseEntity<User> login(@RequestParam @NotBlank(message = "用户名不为空")@Size(min = 3,max = 10,message = "用户名不合法") String username,
                                      @RequestParam @NotBlank(message = "密码不为空")@Size(min = 5,max = 12,message = "密码不合法") String password){
        User user = userService.getUserByName(username);

        if(user == null || !user.getPassword().equals(password)){
            throw new UserIsExistException("用户名或密码错误");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
