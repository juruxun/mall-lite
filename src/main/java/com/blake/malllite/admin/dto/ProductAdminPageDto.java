package com.blake.malllite.admin.dto;

import lombok.Data;
@Data
public class ProductAdminPageDto {
    //页码&&大小
    private Integer page=1;
    private Integer size=10;
    //搜索条件
    private String name;
    private Integer status;


}
