package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.sqy.watchs.watchstore.pojo.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> listSearch(String key);
}
