package com.sqy.watchs.watchstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.DisecernDao;
import com.sqy.watchs.watchstore.pojo.entity.Discern;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import com.sqy.watchs.watchstore.service.DisecernService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisecernServiceImpl extends HoshiService<DisecernDao, Discern> implements DisecernService {
    @Override
    public List<Discern> search(String key) {
        List<Discern> discernList;
        QueryWrapper<Discern> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",key);
        discernList = list(queryWrapper);
        return discernList;
    }

    @Override
    public List<Discern> listSearchByUserId(String key, String userId) {
        List<Discern> discernList;
        QueryWrapper<Discern> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",key).like("user_id", userId);
        discernList = list(queryWrapper);
        return discernList;
    }
}
