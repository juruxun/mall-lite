package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.CartAddDto;
import com.blake.malllite.dto.CartUpdateDto;
import com.blake.malllite.entity.Cart;
import com.blake.malllite.service.CartService;
import com.blake.malllite.vo.CartDetailVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController{
    private CartService cartService;
    @PostMapping
    public Result<Void> addCart(@RequestBody CartAddDto addDto){
        cartService.addCart(addDto);
        return Result.success(null);
    }
    @GetMapping("/list")
    public Result<List<CartDetailVo>> getCartList(){
        List<CartDetailVo> list = cartService.getCartList();
        return Result.success(list);
    }
    @PutMapping("/update")
    public Result<Void> updateCart(@RequestBody CartUpdateDto dto){
        cartService.updateCart(dto);
        return Result.success(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return Result.success(null);

    }

}
