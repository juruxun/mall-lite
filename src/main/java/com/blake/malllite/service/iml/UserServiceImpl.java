package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.injector.methods.SelectList;
import com.blake.malllite.common.Result;
import com.blake.malllite.entity.User;
import com.blake.malllite.mapper.UserMapper;
import com.blake.malllite.service.UserService;
import jakarta.annotation.Resource;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;




    @Override
    public Result<List<User>> listUsers() {
        List<User> usersList = userMapper.selectList(null);
        return Result.success(usersList);
    }



}
