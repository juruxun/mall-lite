package com.blake.malllite.admin.dto;

import lombok.Data;

//后台商品状态管理Put请求，需要商品id和修改后的状态
@Data
public class ProductStatusDto {
    private Long id;
    private Integer status;

}
