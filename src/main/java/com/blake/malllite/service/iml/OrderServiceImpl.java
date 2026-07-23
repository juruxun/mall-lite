package com.blake.malllite.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blake.malllite.common.Result;
import com.blake.malllite.common.UserHolder;
import com.blake.malllite.dto.OrderStatusUpdateDto;
import com.blake.malllite.entity.Cart;
import com.blake.malllite.entity.Order;
import com.blake.malllite.entity.OrderItem;
import com.blake.malllite.entity.Product;
import com.blake.malllite.mapper.CartMapper;
import com.blake.malllite.mapper.OrderItemMapper;
import com.blake.malllite.mapper.OrderMapper;
import com.blake.malllite.mapper.ProductMapper;
import com.blake.malllite.service.OrderService;
import com.blake.malllite.vo.OrderDetailVo;
import com.blake.malllite.vo.OrderListVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CartMapper cartMapper;


    @Transactional
    @Override
    public Result<String> addOrder() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser();
        // 2. 查询当前用户购物车
        LambdaQueryWrapper<Cart> cartWrapper =
                new LambdaQueryWrapper<>();
        cartWrapper.eq(Cart::getUserId, userId);
        List<Cart> carts =
                cartMapper.selectList(cartWrapper);
        // 3. 判断购物车是否为空
        if(carts.isEmpty()){
            throw new RuntimeException("购物车为空");
        }
        // 保存订单总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        // 保存订单详情
        List<OrderItem> orderItems =
                new ArrayList<>();
        // 4. 遍历购物车
        for(Cart cart:carts){
            // 查询商品
            Product product =
                    productMapper.selectById(
                            cart.getProductId()
                    );
            // 商品不存在判断
            if(product==null){
                throw new RuntimeException(
                        "商品不存在"
                );
            }
            // 计算当前商品总价
            BigDecimal itemTotalPrice =
                    product.getPrice()
                            .multiply(
                                    new BigDecimal(
                                            cart.getQuantity()
                                    )
                            );
            // 累加订单金额
            totalAmount =
                    totalAmount.add(
                            itemTotalPrice
                    );
            // 创建订单详情对象
            OrderItem item =
                    new OrderItem();

            // 商品快照
            item.setProductId(
                    product.getId()
            );
            item.setProductName(
                    product.getName()
            );
            item.setProductPrice(
                    product.getPrice()
            );
            item.setQuantity(
                    cart.getQuantity()
            );
            item.setTotalPrice(
                    itemTotalPrice
            );

            // 先保存到集合
            orderItems.add(item);

        }
        // 5. 生成订单号
        String orderNo =
                "ORDER"
                        +
                        System.currentTimeMillis();
        // 6. 创建订单主表
        Order order =
                new Order();

        order.setOrderNo(orderNo);


        order.setUserId(userId);


        order.setTotalAmount(
                totalAmount
        );


        // 待支付
        order.setStatus(0);
        // 插入order表
        orderMapper.insert(order);
        // 7. 获取订单id
        Long orderId = order.getId();
        // 8. 保存订单详情
        for(OrderItem item:orderItems){
            // 给订单详情绑定订单id
            item.setOrderId(orderId);
            orderItemMapper.insert(item);
        }
        // 9. 清空购物车
        cartMapper.delete(cartWrapper);
        // 10. 返回订单号
        return Result.success(orderNo);
    }

    @Override
    public List<OrderListVo> getOrderList() {
        Long userId = UserHolder.getUser();
        LambdaQueryWrapper<Order> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId,userId);
        List<Order> orders = orderMapper.selectList(wrapper);
        
        List<OrderListVo> result =new ArrayList<>();
        for (Order order : orders) {
            //创建OrderListVo对象
            OrderListVo vo = new OrderListVo();
            //Entity复制给vo(entity转vo)
            BeanUtils.copyProperties(order,vo);
            //4. 查询订单商品
            LambdaQueryWrapper<OrderItem> itemWrapper =
                    new LambdaQueryWrapper<>();
            itemWrapper.eq(
                    OrderItem::getOrderId,
                    order.getId()
            );
            List<OrderItem> orderItems = orderItemMapper.selectList(itemWrapper);
            //设置商品明细（order_Item表）
            vo.setItems(orderItems);
            result.add(vo);
        }
        
    return result;
    }

    @Override
    public OrderDetailVo getOrderDetailById(Long id) {
        Long userId = UserHolder.getUser();
        Order order = orderMapper.selectById(id);
        if (order==null){
            throw new RuntimeException("订单不存在");
        }
        //权限判断(订单归属校验，确认order中的用户id和当前用户的id相同)
        if (!order.getUserId().equals(userId)){
            throw new RuntimeException("无权限查看该订单");
        }

        LambdaQueryWrapper<OrderItem> wrapper =new LambdaQueryWrapper<>();
        //查询订单商品(sql)
        wrapper.eq(OrderItem::getOrderId,id);
        List<OrderItem> items= orderItemMapper.selectList(wrapper);

        //封装VO(给前端的数据：即用户看到的数据)
        OrderDetailVo vo = new OrderDetailVo();
        BeanUtils.copyProperties(order,vo);

        return vo;



    }

    @Override
    public void updateStatus(OrderStatusUpdateDto dto) {
        Long userId = UserHolder.getUser();
        LambdaQueryWrapper<Order> wrapper =
                new LambdaQueryWrapper<>();

        wrapper.eq(Order::getId,dto.getId())
                .eq(Order::getUserId,userId);

        Order order = orderMapper.selectOne(wrapper);

        if(order==null){
            throw new RuntimeException("订单不存在");
        }
        //状态校验
        if(order.getStatus()!=0){
            throw new RuntimeException(
                    "当前订单状态不能修改"
            );

        }
        if(dto.getStatus()!=1
                && dto.getStatus()!=2){
            throw new RuntimeException("非法状态");
        }
        //状态修改
        order.setStatus(dto.getStatus());
        orderMapper.updateById(order);


    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Long userId = UserHolder.getUser();
        LambdaQueryWrapper<Order> wrapper =
                new LambdaQueryWrapper<>();

       wrapper.eq(Order::getUserId,userId).eq(Order::getId,id);
        Order order = orderMapper.selectOne(wrapper);
        if (order==null){
            throw new RuntimeException("订单不存在");
        }
        if (order.getStatus()!=1){
            throw new RuntimeException("当前订单状态不能删除");
        }
        LambdaQueryWrapper<OrderItem> oderItemWrapper = new LambdaQueryWrapper<>();
        oderItemWrapper.eq(OrderItem::getOrderId,id);
        orderItemMapper.delete(oderItemWrapper);

        orderMapper.deleteById(id);
    }

    @Override
    @Transactional //对于涉及多个表的操作，需要保证事物统一性
    public void cancelOrder(Long id) {
        Long userId = UserHolder.getUser();
        Order order = orderMapper.selectById(id);
        if (order==null){
            throw new RuntimeException("订单不存在");
        }
        if (!order.getUserId().equals(userId)){
            throw new  RuntimeException("无权限取消订单");
        }
        if (order.getStatus()!=0){
            throw new RuntimeException("当前订单状态无法取消订单");
        }
        order.setStatus(2);
        orderMapper.updateById(order);




    }


}
