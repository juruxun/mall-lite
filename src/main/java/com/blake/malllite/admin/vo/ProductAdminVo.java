package com.blake.malllite.admin.vo;


import com.blake.malllite.entity.Product;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductAdminVo {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
    private String  categoryName;
    private Integer status;
    private LocalDateTime createTime;



}
