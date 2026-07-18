package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blake.malllite.common.Result;
import com.blake.malllite.dto.CategoryCreateDto;
import com.blake.malllite.dto.CategoryUpdateDto;
import com.blake.malllite.entity.Category;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CategoryMapper;
import com.blake.malllite.mapper.ProductMapper;
import com.blake.malllite.service.CategoryService;
import com.blake.malllite.vo.CategoryDetailVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ProductMapper productMapper;
    @Override
    public List<Category> listCategories() {
        List<Category> list = categoryMapper.selectList(null);
        return list;
    }

    @Override
    public CategoryDetailVo getCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        if (category==null){
            return null;
        }
        CategoryDetailVo vo =new CategoryDetailVo();
        BeanUtils.copyProperties(category,vo);
        return vo;

    }

    @Override
    public void addCategory(CategoryCreateDto dto) {

        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName,dto.getName());
        Category existCategory = categoryMapper.selectOne(wrapper);
        if (existCategory!=null){
            throw new RuntimeException("分类已存在");
        }
        Category category= new Category();
        BeanUtils.copyProperties(dto,category);
        categoryMapper.insert(category);


    }

    @Override
    public void updateCategory(CategoryUpdateDto dto) {
        Category category =categoryMapper.selectById(dto.getId());
        if (category==null){
            throw new RuntimeException("分类不存在");
        }
        LambdaQueryWrapper<Category> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName,dto.getName());
        wrapper.ne(Category::getId,dto.getId());

        Category existCategory =
                categoryMapper.selectOne(wrapper);
        if(existCategory != null){

            throw new RuntimeException("分类名称已存在");

        }


        Category updateCategory = new Category();
        BeanUtils.copyProperties(dto,updateCategory);
        categoryMapper.updateById(updateCategory);

    }

    @Override
    public void deleteCategory(Long id) {
        Category category =categoryMapper.selectById(id);
        if (category==null){
            throw new RuntimeException("分类不存在");
        }
        LambdaQueryWrapper<Product> wrapper =new LambdaQueryWrapper<>();
         wrapper.eq(Product::getCategoryId, id);
         Long count = productMapper.selectCount(wrapper);
         if (count>0){
             throw new RuntimeException("该分类下存在商品,无法删除");
         }
        categoryMapper.deleteById(id);
    }
}
