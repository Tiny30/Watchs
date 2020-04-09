package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.watchs.watchstore.pojo.entity.Order;


import java.util.List;

public interface OrderService extends IService<Order> {
    List<Order> listSearch(String key);
}
