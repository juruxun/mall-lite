package com.blake.malllite.controller;

import com.blake.malllite.common.Result;
import com.blake.malllite.dto.OrderStatusUpdateDto;
import com.blake.malllite.service.OrderService;
import com.blake.malllite.vo.OrderDetailVo;
import com.blake.malllite.vo.OrderListVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @PostMapping("/addOrder")
    public Result<String> addOrder (){
        return orderService.addOrder();

    }
    @GetMapping("/list")
    public Result<List<OrderListVo>> getOrderList(){
        return Result.success(orderService.getOrderList());
    }

    @GetMapping("/{id}")
    public Result<OrderDetailVo> getOrderDetailById(@PathVariable Long id){
        return  Result.success(orderService.getOrderDetailById(id));

    }

    @PutMapping("/{id}/status")
    public Result<Void> updateStatus(@RequestBody OrderStatusUpdateDto dto) {
        orderService.updateStatus(dto);
        return Result.success(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return Result.success(null);

    }
    @PutMapping("/cancel/{id}")
    public Result<Void> cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
        return Result.success(null);
    }


}
