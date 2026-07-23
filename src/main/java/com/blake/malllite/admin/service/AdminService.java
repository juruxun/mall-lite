package com.blake.malllite.admin.service;

import com.blake.malllite.admin.dto.AdminLoginDto;
import com.blake.malllite.admin.vo.AdminLoginVo;

public interface AdminService {
    AdminLoginVo login(AdminLoginDto dto);
}
