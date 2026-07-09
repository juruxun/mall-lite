package com.blake.malllite.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data ;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Locale;
@Data
@TableName("ums_user")
public class User{
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}

