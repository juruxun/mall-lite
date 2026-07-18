package com.blake.malllite.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CategoryDetailVo {
    private Long id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime  updateTime;
}
