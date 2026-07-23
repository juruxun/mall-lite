package com.blake.malllite.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.admin.dto.ProductAdminCreateDto;
import com.blake.malllite.admin.dto.ProductAdminPageDto;
import com.blake.malllite.admin.dto.ProductAdminUpdateDto;
import com.blake.malllite.admin.dto.ProductStatusDto;
import com.blake.malllite.admin.vo.ProductAdminVo;
import com.blake.malllite.dto.ProductUpdateDto;

/*
* 后台商品管理模块
* 面向管理员：管理商品、修改库存、上下架、删除商品
 */
public interface AdminProductService {
    //后台分页查询功能接口
    Page<ProductAdminVo> page(ProductAdminPageDto dto);
    void addProduct(ProductAdminCreateDto dto);
    void upDateProduct(ProductAdminUpdateDto dto);
    void updateStatus(ProductStatusDto dto);
    void deleteProduct(Long id);

}
