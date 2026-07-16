package com.blake.malllite.dto;

import lombok.Data;

@Data
public class ProductPageDto {
    private Integer page=1;
    private Integer size=10;
    private String name ;
    private Integer status;


}
