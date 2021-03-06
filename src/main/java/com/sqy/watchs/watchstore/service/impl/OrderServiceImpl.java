package com.sqy.watchs.watchstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.OrderDao;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import com.sqy.watchs.watchstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends HoshiService<OrderDao, Order> implements OrderService {
    public List<Order> listSearch(String num, String userId) {
        List<Order> orderList;
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", userId).and(wrapper->{
            wrapper.like("name", num).or()
                    .like("num", num).or()
                    .like("description", num);
        });
        if(needPage()){
            orderList = page(getPage(),queryWrapper).getRecords();
        }else {
            orderList = list(queryWrapper);
        }
        return orderList;
    }

    @Override
    public List<Order> search(String key) {
        return listSearch(key, "");
    }
}
