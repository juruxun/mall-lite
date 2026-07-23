package com.blake.malllite.admin.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.admin.dto.ProductAdminCreateDto;
import com.blake.malllite.admin.dto.ProductAdminPageDto;
import com.blake.malllite.admin.dto.ProductAdminUpdateDto;
import com.blake.malllite.admin.dto.ProductStatusDto;
import com.blake.malllite.admin.vo.ProductAdminVo;
import com.blake.malllite.entity.Category;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CategoryMapper;
import com.blake.malllite.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AdminProductServiceImpl implements AdminProductService{
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public Page<ProductAdminVo> page(ProductAdminPageDto dto) {
        //业务实现逻辑（后台分页查询模块）
        //1.创建分页对象
        Page<Product> page =new Page<>(dto.getPage(),dto.getSize());
        //2.条件查询
        LambdaQueryWrapper<Product> wrapper =new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(dto.getName()),Product::getName,dto.getName());
        wrapper.eq(dto.getStatus()!=null,Product::getStatus,dto.getStatus());
        //查询
        Page<Product> result=productMapper.selectPage(page,wrapper);

        Page<ProductAdminVo> voPage = new Page<>();
        voPage.setPages(result.getPages());
        voPage.setCurrent(result.getCurrent());
        voPage.setSize(result.getSize());
        voPage.setTotal(result.getTotal());
        List<ProductAdminVo> voList = result.getRecords().stream().map(product -> {
            ProductAdminVo vo = new ProductAdminVo();
            BeanUtils.copyProperties(product, vo);
            Category category =
                    categoryMapper.selectById(product.getCategoryId());
            vo.setCategoryName(category.getName());
            return vo;
        }).toList();
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public void addProduct(ProductAdminCreateDto dto) {
        //第一步：判断分类是否存在
        Category category =categoryMapper.selectById(dto.getCategoryId());
        if (category==null){
            throw new RuntimeException("商品分类不存在");
        }
        //第二步：商品名称不能重复
        LambdaQueryWrapper<Product> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Product::getName,dto.getName());
        //查询
        Product exit =productMapper.selectOne(wrapper);
        if (exit!=null){
        throw new RuntimeException("商品已存在");
        }
        //商品不存在则添加，将DTO的商品字段复制到Product
        Product product = new Product();
        BeanUtils.copyProperties(dto,product);
        //设置默认字段; 0:下架，1：上架
        product.setStatus(0);
        //在数据库product保存
        productMapper.insert(product);

    }

    @Override
    public void upDateProduct(ProductAdminUpdateDto dto) {
        //查询商品
        Product product = productMapper.selectById(dto.getId());
        if (product==null){
            throw new RuntimeException("该商品不存在");
        }
        //校验分类;因为修改可能要改别商品的分类如：手机修改为平板
        Category category = categoryMapper.selectById(dto.getCategoryId());
        if (category==null){
            throw new RuntimeException("该分类不存在");
        }
        //商品名称重复性查验：修改商品自己不能算重复
        LambdaQueryWrapper<Product> wrapper =
                new LambdaQueryWrapper<>();

        wrapper.eq(Product::getName,dto.getName())
                .ne(Product::getId, dto.getId());//表示对SQL语句：where name=dto.getName and id!=dto.getId,

        //修改商品时要判断想要修改为的商品名称是否存在
        //必须查询同名数据+排除自己，否则自己修改自己会误判重复。
        Product exist = productMapper.selectOne(wrapper);
        if (exist!=null){
            throw new RuntimeException("该商品名称已存在");
        }
        BeanUtils.copyProperties(dto,product);
        productMapper.updateById(product);

    }

    //后台商品管理模块（商品状态管理：上架与下架）
    @Override
    public void updateStatus(ProductStatusDto dto) {
        //判断商品
        Product product = productMapper.selectById(dto.getId());
        if (product==null){
            throw new RuntimeException("商品不存在");
        }
        //状态合法性校验
        if (dto.getStatus()!=0&&dto.getStatus()!=1){
            throw new RuntimeException("非法商品状态");
        }
        //修改状态
        product.setStatus(dto.getStatus());
        productMapper.updateById(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productMapper.selectById(id);
        if (product==null){
            throw new RuntimeException("商品不存在");
        }
        //判断商品状态
        if (product.getStatus()==1){
            throw new RuntimeException("商品已上架，不能删除");
        }
        product.setDeleted(0);
        productMapper.updateById(product);



    }


}
