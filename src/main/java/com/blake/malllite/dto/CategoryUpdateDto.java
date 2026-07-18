package com.blake.malllite.dto;


import lombok.Data;

@Data
public class CategoryUpdateDto {
    private Long id;
    private String name;
    private Integer status;
}
