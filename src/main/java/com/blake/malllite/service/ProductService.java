package com.blake.malllite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.dto.ProductPageDto;
import com.blake.malllite.dto.ProductUpdateDto;
import com.blake.malllite.entity.Product;
import com.blake.malllite.vo.ProductDetailVo;
import com.blake.malllite.vo.ProductPageVo;

import java.util.List;

public interface ProductService {
    Result<List<Product>> listProducts();
    Result<ProductDetailVo>getProductById(Long id);
    void addProduct(ProductDto dto);
    Page<ProductPageVo>page(ProductPageDto dto);
    void updateProduct(ProductUpdateDto dto);
    void deleteProduct(Long id);
}
