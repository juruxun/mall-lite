package com.blake.malllite.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@TableName("cart")
public class Cart {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    @TableLogic
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
