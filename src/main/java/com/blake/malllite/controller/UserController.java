package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.common.UserHolder;
import com.blake.malllite.dto.LoginDto;
import com.blake.malllite.entity.User;
import com.blake.malllite.service.UserService;
import com.blake.malllite.util.JwtUtil;
import com.blake.malllite.vo.UserInfoVo;
import jakarta.annotation.Resource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

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
    public Result<?>login(@RequestBody LoginDto loginDto){
        String token = JwtUtil.generateToken(1001,"admin");

        return Result.success(token);
    }

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello Mall-lite");
    }

    @GetMapping("/test")
    public String test(){

        Long userId =
                UserHolder.getUser();


        return "当前用户id:"
                + userId;

    }
    @GetMapping("/info")
    public Result<UserInfoVo> info() {
        UserInfoVo userInfoVo = userService.getCurrentUser();
        return Result.success(userInfoVo
        );
    }



}
