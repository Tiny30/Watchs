package com.sqy.watchs.watchstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
}
