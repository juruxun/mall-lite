package com.blake.malllite.admin.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAdminCreateDto {
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private Integer stock;
    private String description;



}
