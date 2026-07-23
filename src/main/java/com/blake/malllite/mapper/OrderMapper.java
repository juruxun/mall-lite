package com.blake.malllite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blake.malllite.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
