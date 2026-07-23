package com.blake.malllite.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.admin.dto.ProductAdminCreateDto;
import com.blake.malllite.admin.dto.ProductAdminPageDto;
import com.blake.malllite.admin.dto.ProductAdminUpdateDto;
import com.blake.malllite.admin.dto.ProductStatusDto;
import com.blake.malllite.admin.service.impl.AdminProductService;
import com.blake.malllite.admin.vo.ProductAdminVo;
import com.blake.malllite.common.Result;
import com.blake.malllite.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {
    @Resource
    private AdminProductService adminProductService;
    public ProductMapper productMapper;

    @GetMapping("/list")
    public Result<Page<ProductAdminVo>> list(ProductAdminPageDto dto) {
        return Result.success(adminProductService.page(dto));
    }
    @PostMapping
    public Result<Void> addProduct(@RequestBody ProductAdminCreateDto dto){
     adminProductService.addProduct(dto);
     return Result.success(null);
    }
    @PutMapping
    public Result<Void> updateProduct(@RequestBody ProductAdminUpdateDto dto){
        adminProductService.upDateProduct(dto);
        return Result.success(null);
    }
    @PutMapping
    public Result<Void>updateStatus(@RequestBody ProductStatusDto dto){
        adminProductService.updateStatus(dto);
        return Result.success(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void>deleteProduct(@PathVariable Long id){
        adminProductService.deleteProduct(id);
        return Result.success(null);
    }

}
