package com.sqy.watchs.watchstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.OrderDao;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import com.sqy.watchs.watchstore.pojo.entity.Product;
import com.sqy.watchs.watchstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends HoshiService<OrderDao, Order> implements OrderService {
    public List<Order> listSearch(String key) {
        key = "%" + key + "%";
        List<Order> orderList;
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
        queryWrapper.like("num",key);
        if(needPage()){
            orderList = page(getPage(),queryWrapper).getRecords();

        }else {
            orderList = list(queryWrapper);
        }
        return orderList;
    }
}
