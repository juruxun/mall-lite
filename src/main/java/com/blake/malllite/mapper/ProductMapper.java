package com.blake.malllite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blake.malllite.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {


}

