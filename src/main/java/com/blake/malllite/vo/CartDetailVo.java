package com.blake.malllite.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDetailVo {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String productName;
    private String productImage;
    private BigDecimal price;
    private BigDecimal totalPrice;


}
