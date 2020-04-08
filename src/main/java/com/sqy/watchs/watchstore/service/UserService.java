package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.watchs.watchstore.pojo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listBySearch(String key);
    /***
     * 根据账号查询用户信息
     */
    List<User> getByAccount(String account);
}
