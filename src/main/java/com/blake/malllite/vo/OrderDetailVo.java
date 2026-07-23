package com.blake.malllite.vo;

import com.blake.malllite.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderDetailVo {
    private Long id;//订单
    private String orderNo;//订单编号
    private Long userId;//用户id
    private BigDecimal totalAmount;//总金额
    /*
    * 状态
    *
    * */
    private LocalDateTime createTime;
    private Integer status;
    //商品详情（商品列表）
    private List<OrderItem> list;

}
