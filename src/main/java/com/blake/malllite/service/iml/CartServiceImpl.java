package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blake.malllite.common.UserHolder;
import com.blake.malllite.dto.CartAddDto;
import com.blake.malllite.dto.CartUpdateDto;
import com.blake.malllite.entity.Cart;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CartMapper;
import com.blake.malllite.mapper.ProductMapper;
import com.blake.malllite.service.CartService;

import com.blake.malllite.vo.CartDetailVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public void addCart(CartAddDto cartAddDto) {
        Long userID= UserHolder.getUser();
        Product product = productMapper.selectById(cartAddDto.getProductId());
        if (product==null){
             throw  new RuntimeException("商品不存在");
        }
        /*
        * 查询购物车是否已有商品LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        * 查询条件eq()..,
        * 对应SQL
        * SELECT *
        FROM cart
        WHERE user_id=userId(前端上传的)
        AND product_id=productId(前端上传的)
        * */
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId,userID);
        wrapper.eq(Cart::getProductId,cartAddDto.getProductId());
        Cart cart = cartMapper.selectOne(wrapper);

        //判断购物车（cart）是否存在
        if (cart!=null){
            cart.setQuantity(cart.getQuantity()+cartAddDto.getQuantity());
            cartMapper.updateById(cart);
        }else {

            Cart newCart = new Cart();
            newCart.setUserId(userID);
            newCart.setProductId(cartAddDto.getProductId());
            newCart.setQuantity(cartAddDto.getQuantity());
            cartMapper.insert(newCart);
        }
    }

    @Override
    public List<CartDetailVo> getCartList() {
        Long userId = UserHolder.getUser();
        LambdaQueryWrapper<Cart> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId,userId);
        List<Cart> carts = cartMapper.selectList(wrapper);
        List<CartDetailVo> voList = new ArrayList<>();
        for (Cart cart:carts ){
            Product product = productMapper.selectById(cart.getProductId());
            if (product==null){
                continue;
            }
            CartDetailVo vo = new CartDetailVo();

            BeanUtils.copyProperties(cart,vo);

            vo.setProductName(product.getName());

            vo.setPrice(product.getPrice());

            vo.setTotalPrice(product.getPrice().multiply(new BigDecimal(cart.getQuantity())));

            voList.add(vo);
        }

        return voList;

    }

    @Override
    public void updateCart(CartUpdateDto dto) {
        Long userId = UserHolder.getUser();
        Cart cart = cartMapper.selectById(dto.getId());
        if (cart==null){
            throw new RuntimeException("当前购物车不存在");
        }
        if (!cart.getUserId().equals(userId)){
            throw new RuntimeException("无修改权限");
        }
        Integer quantity = dto.getQuantity();
        if (quantity==null||quantity<=0){
            throw new RuntimeException("商品数量必须大于0");
        }
        cart.setQuantity(quantity);
        cartMapper.updateById(cart);
    }

    @Override
    public void deleteCart(Long id) {
        Long userId = UserHolder.getUser();
        Cart cart = cartMapper.selectById(id);
        if (cart==null){
            throw new RuntimeException("购物车不存在");
        }
        if (!cart.getUserId().equals(userId)){
            throw new RuntimeException("无修改权限");
        }
        cartMapper.deleteById(id);


    }
}
