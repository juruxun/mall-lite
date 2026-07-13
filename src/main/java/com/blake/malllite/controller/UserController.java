package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.entity.User;
import com.blake.malllite.service.UserService;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Result<List<User>> listUsers(){
        return userService.listUsers();
    }

    @PostMapping("/login")
    public Result<?>login(){
        return null;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
