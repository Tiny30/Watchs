package com.sqy.watchs.watchstore.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqy.watchs.watchstore.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
