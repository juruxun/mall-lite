package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.CategoryCreateDto;
import com.blake.malllite.dto.CategoryUpdateDto;
import com.blake.malllite.entity.Category;
import com.blake.malllite.service.CategoryService;
import com.blake.malllite.vo.CategoryDetailVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/list")
    public Result<List<Category>>list(){
        List<Category> list = categoryService.listCategories();
        return Result.success(list);
    }
    @GetMapping("/{id}")
   public   Result<CategoryDetailVo> getCategoryById(@PathVariable Long id){
        return Result.success(categoryService.getCategoryById(id));


    }
    @PostMapping
    public Result<Void>addCategory(@RequestBody CategoryCreateDto dto){
        categoryService.addCategory(dto);
        return Result.success(null);


    }
    @PutMapping("/update")
    public Result<Void>  updateCategory(@RequestBody CategoryUpdateDto dto){
       categoryService.updateCategory(dto);
       return  Result.success(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(Long id){
        categoryService.deleteCategory(id);
        return Result.success(null);


    }

}
