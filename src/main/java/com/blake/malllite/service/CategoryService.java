package com.blake.malllite.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blake.malllite.dto.CategoryCreateDto;
import com.blake.malllite.dto.CategoryUpdateDto;
import com.blake.malllite.entity.Category;
import com.blake.malllite.vo.CategoryDetailVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService  {
    List<Category> listCategories();
    CategoryDetailVo getCategoryById(Long id);
    void addCategory(CategoryCreateDto dto);
    void updateCategory(CategoryUpdateDto dto);
    void deleteCategory(Long id);
}
