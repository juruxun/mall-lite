package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.injector.methods.SelectList;
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
    public List<User> listUsers() {
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
