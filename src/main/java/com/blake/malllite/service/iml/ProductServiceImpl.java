package com.blake.malllite.service.iml;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.entity.Category;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CategoryMapper;
import com.blake.malllite.mapper.ProductMapper;
import com.blake.malllite.service.ProductService;
import com.blake.malllite.vo.ProductDetailVo;
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


}
