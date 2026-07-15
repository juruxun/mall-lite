package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.entity.Product;
import com.blake.malllite.service.ProductService;
import com.blake.malllite.vo.ProductDetailVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("list")
    public Result<List<Product>> listProduct(){
        return productService.listProducts();
    }
    @GetMapping("/{id}")
    public Result<ProductDetailVo> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public  Result<Void> addProduct( @RequestBody ProductDto dto){
        productService.addProduct(dto);
        return  Result.success(null);

    }

}
