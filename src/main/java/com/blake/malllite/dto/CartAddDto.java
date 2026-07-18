package com.blake.malllite.dto;

import lombok.Data;

@Data
public class CartAddDto {
    private Long productId;
    private Integer quantity;
}
