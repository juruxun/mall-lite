package com.blake.malllite.service;


import com.blake.malllite.common.Result;
import com.blake.malllite.entity.User;

import java.util.List;

public interface UserService  {
    Result<List<User>> listUsers();


}
