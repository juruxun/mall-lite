package com.blake.malllite.admin.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAdminUpdateDto {
    private  Long id;
    private  String name;
    private Long categoryId;
    private BigDecimal price;
    private Integer stock;
    private String description;




}
