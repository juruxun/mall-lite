package com.blake.malllite.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductPageVo {
        private Long id;
        private String name;
        private BigDecimal price;
        private Integer stock;
        private String categoryName;



}
