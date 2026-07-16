package com.blake.malllite.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductUpdateDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;

}
