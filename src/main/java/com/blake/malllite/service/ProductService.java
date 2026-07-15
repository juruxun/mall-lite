package com.blake.malllite.service;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.entity.Product;
import com.blake.malllite.vo.ProductDetailVo;

import java.util.List;

public interface ProductService {
    Result<List<Product>> listProducts();
    Result<ProductDetailVo>getProductById(Long id);
    void addProduct(ProductDto dto);
}
