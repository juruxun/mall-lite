package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blake.malllite.common.Result;
import com.blake.malllite.common.UserHolder;
import com.blake.malllite.dto.LoginDto;
import com.blake.malllite.entity.User;
import com.blake.malllite.mapper.UserMapper;
import com.blake.malllite.service.UserService;
import com.blake.malllite.util.JwtUtil;
import com.blake.malllite.vo.LoginVo;
import com.blake.malllite.vo.UserInfoVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<List<User>> listUsers() {
        List<User> usersList = userMapper.selectList(null);
        if (usersList == null || usersList.isEmpty()) {
            return Result.failed("获取用户列表失败，数据库无数据");

        }
        return Result.success(usersList);
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, loginDto.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (user==null){
           throw new RuntimeException("用户名或密码错误");
        }
        if (!user.getPassword().equals(loginDto.getPassword())){
            throw new RuntimeException("用户名或密码错误");
        }
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername(user.getUsername());
        String token = JwtUtil.generateToken(user.getId(),user.getUsername());

        loginVo.setToken(token);
        return loginVo;


    }

    @Override
    public UserInfoVo getCurrentUser() {
        Long userId = UserHolder.getUser();
        if (userId==null){
            return null;
        }

        User user = userMapper.selectById(userId);
        if (user==null){
            return null;
        }

        UserInfoVo vo = new UserInfoVo();

        BeanUtils.copyProperties(user, vo);

        return vo;
    }


}
