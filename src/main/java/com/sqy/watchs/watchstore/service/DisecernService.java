package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.watchs.watchstore.pojo.entity.Discern;
import com.sqy.watchs.watchstore.pojo.entity.Order;

import java.util.List;

public interface DisecernService extends IHoshiService<Discern> {
    List<Discern> listSearchByUserId(String num, String userId);
}
