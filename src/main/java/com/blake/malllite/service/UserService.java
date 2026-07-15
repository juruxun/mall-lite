package com.blake.malllite.service;


import com.blake.malllite.common.Result;
import com.blake.malllite.dto.LoginDto;
import com.blake.malllite.entity.User;
import com.blake.malllite.vo.LoginVo;
import com.blake.malllite.vo.UserInfoVo;

import java.util.List;

public interface UserService  {
    Result<List<User>> listUsers();
    LoginVo login(LoginDto loginDto);
    UserInfoVo getCurrentUser();

}
