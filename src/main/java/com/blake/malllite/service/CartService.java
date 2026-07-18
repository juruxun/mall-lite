package com.blake.malllite.service;

import com.blake.malllite.dto.CartAddDto;
import com.blake.malllite.dto.CartUpdateDto;
import com.blake.malllite.entity.Cart;
import com.blake.malllite.vo.CartDetailVo;

import java.util.List;


public interface CartService {
    void addCart(CartAddDto cartAddDto);
    List<CartDetailVo> getCartList();
    void updateCart(CartUpdateDto dto);
    void deleteCart(Long id);
}
