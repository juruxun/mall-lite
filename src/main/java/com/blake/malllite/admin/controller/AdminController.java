package com.blake.malllite.admin.controller;

import com.blake.malllite.admin.dto.AdminLoginDto;
import com.blake.malllite.admin.vo.AdminLoginVo;
import com.blake.malllite.admin.service.AdminService;
import com.blake.malllite.common.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping
    public Result<AdminLoginVo> login(@RequestBody AdminLoginDto dto){
        return Result.success(adminService.login(dto));
    }

}
