package com.blake.malllite.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailVo {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String categoryName;

}
