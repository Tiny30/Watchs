package com.sqy.watchs.watchstore.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.UserDao;
import com.sqy.watchs.watchstore.pojo.entity.User;
import com.sqy.watchs.watchstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends HoshiService<UserDao, User> implements UserService {
    /**
     * 根据关键字查询所有信息
     * @param key
     * @return lsit
     */
    @Override
    public List<User> listBySearch(String key) {
        key = "%"+key+"%" ;
        List<User> userList;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", key)
                .or().like("id", key);
        if (needPage()) {
            userList = page(getPage(), wrapper).getRecords();
        } else {
            userList = list(wrapper);
        }
        return userList;
    }
}
