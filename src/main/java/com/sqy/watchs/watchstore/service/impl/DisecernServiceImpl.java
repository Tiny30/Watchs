package com.sqy.watchs.watchstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.DisecernDao;
import com.sqy.watchs.watchstore.pojo.entity.Disecern;
import com.sqy.watchs.watchstore.service.DisecernService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisecernServiceImpl extends HoshiService<DisecernDao, Disecern> implements DisecernService {
    @Override
    public List<Disecern> search(String key) {
        List<Disecern> disecernList;
        QueryWrapper<Disecern> queryWrapper = new QueryWrapper<Disecern>();
        queryWrapper.select("status").eq("num",key);
        disecernList = list(queryWrapper);
        return disecernList;
    }
}
