package com.sqy.watchs.watchstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.watchs.watchstore.dao.ProductDao;
import com.sqy.watchs.watchstore.pojo.entity.Product;
import com.sqy.watchs.watchstore.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends HoshiService<ProductDao, Product> implements ProductService {

    @Override
    public List<Product> listSearch(String key) {
        key = "%" + key + "%";
        List<Product> productList;
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>();
        queryWrapper.like("name",key);
        if(needPage()){
            productList = page(getPage(),queryWrapper).getRecords();
        }else {
            productList = list(queryWrapper);
        }
        return productList;
    }

    @Override
    public List<Product> search(String key) {
        return listSearch(key);
    }
}
