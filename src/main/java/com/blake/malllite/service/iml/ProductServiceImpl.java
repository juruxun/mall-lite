package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.dto.ProductPageDto;
import com.blake.malllite.dto.ProductUpdateDto;
import com.blake.malllite.entity.Category;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CategoryMapper;
import com.blake.malllite.mapper.ProductMapper;
import com.blake.malllite.service.ProductService;
import com.blake.malllite.vo.ProductDetailVo;
import com.blake.malllite.vo.ProductPageVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public Result<List<Product>> listProducts() {
        List<Product> list = productMapper.selectList(null);
        return Result.success(list);
    }

    @Resource
    private  CategoryMapper categoryMapper;
    @Override
    public Result<ProductDetailVo> getProductById(Long id) {
        Product product = productMapper.selectById(id);
        if (product==null){
            return Result.failed("商品不存在");
        }
        //查询分类
        Category category = categoryMapper.selectById(product.getCategoryId());

        //封装VO
        ProductDetailVo vo = new ProductDetailVo();
        BeanUtils.copyProperties(product,vo);
        if(category != null){

            vo.setCategoryName(
                    category.getName()
            );

        }


        return Result.success(vo);

    }

    @Override
    public void addProduct(ProductDto dto) {
        Product product =new Product();
        BeanUtils.copyProperties(dto,product);
        productMapper.insert(product);


    }
    @Override
    public Page<ProductPageVo> page(ProductPageDto dto) {
        Page<Product> page=new Page<>(dto.getPage(),dto.getSize());

        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        //用wrapper写sql语句，构建查询条件
        wrapper.like(dto.getName()!=null,//表示是否添加这个查询条件，if!=null,添加这个sql查询条件name like...，代码向下执行
                Product::getName,//表示sql语句 name like...里的name
                dto.getName()); //dto.getName()表示查询内容，如苹果最终的sql语句是name like '%苹果%' %为MyBatis-Plus自动添加

        wrapper.eq(dto.getStatus()!=null,Product::getStatus //equal()
                ,dto.getStatus());
        wrapper.orderByDesc(Product::getCreateTime);//分页按时间排序

        Page<Product> result = productMapper.selectPage(page,wrapper);

        Page<ProductPageVo> voPage = new Page<>();
        voPage.setCurrent(result.getCurrent());
        voPage.setSize(result.getSize());
        voPage.setTotal(result.getTotal());
        List<ProductPageVo> voList = result.getRecords()
                .stream()
                .map(product -> {

                    ProductPageVo pageVo = new ProductPageVo();

                    BeanUtils.copyProperties(product, pageVo);

                    return pageVo;

                })
                .toList();
        voPage.setRecords(voList);

        return voPage;
    }

    @Override
    public void updateProduct(ProductUpdateDto dto) {

         Product product = productMapper.selectById(dto.getId());
         if (product==null){
            throw  new RuntimeException("商品不存在");
         }
        Category category = categoryMapper.selectById(dto.getCategoryId());
         if (category ==null){
             throw new RuntimeException("商品分类不存在");
         }
        Product updateProduct = new Product();
        BeanUtils.copyProperties(dto, updateProduct);
        productMapper.updateById(updateProduct);


    }

    @Override
    public void deleteProduct(Long id) {
        Product product =productMapper.selectById(id);
        if (product==null){
            throw new RuntimeException("商品不存在");
        }
        int i = productMapper.deleteById(id);
        int result = productMapper.deleteById(id);

        if(result==0){
            throw new RuntimeException("删除失败");
        }


    }


}
