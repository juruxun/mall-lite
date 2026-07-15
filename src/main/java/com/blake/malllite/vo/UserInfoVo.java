package com.blake.malllite.vo;

import lombok.Data;

@Data
public class UserInfoVo {
    private Long id;

    private String username;

    private String nickname;

    private String phone;

    private String email;

    private String avatar;
}
