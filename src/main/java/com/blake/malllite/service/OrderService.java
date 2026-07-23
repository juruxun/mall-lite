package com.blake.malllite.service;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.OrderStatusUpdateDto;
import com.blake.malllite.vo.OrderDetailVo;
import com.blake.malllite.vo.OrderListVo;

import java.util.List;

public interface OrderService {
    Result<String> addOrder();
    List<OrderListVo> getOrderList();
    OrderDetailVo getOrderDetailById(Long id);
    void  updateStatus(OrderStatusUpdateDto dto);
    void  deleteOrder(Long id);
    void  cancelOrder(Long id);
}
