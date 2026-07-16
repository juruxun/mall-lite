package com.blake.malllite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blake.malllite.common.Result;
import com.blake.malllite.dto.ProductDto;
import com.blake.malllite.dto.ProductPageDto;
import com.blake.malllite.dto.ProductUpdateDto;
import com.blake.malllite.entity.Product;
import com.blake.malllite.service.ProductService;
import com.blake.malllite.vo.ProductDetailVo;
import com.blake.malllite.vo.ProductPageVo;
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

    @GetMapping
    public Result<Page<ProductPageVo>> page(ProductPageDto dto){

        Page<ProductPageVo> page = productService.page(dto);

        return Result.success(page);
    }
    @PutMapping("/update")
    public Result<Void>updateProduct(@RequestBody ProductUpdateDto updateDto){
        productService.updateProduct(updateDto);
        return Result.success(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void>deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return Result.success(null);

    }



}
