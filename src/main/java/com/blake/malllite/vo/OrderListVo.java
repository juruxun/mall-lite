package com.blake.malllite.vo;

import com.blake.malllite.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderListVo {
    private Long id;
    private String orderNo;
    private BigDecimal totalAmount;
    private Integer status;
    private List<OrderItem> items;

}
