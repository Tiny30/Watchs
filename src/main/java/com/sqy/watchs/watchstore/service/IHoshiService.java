package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface IHoshiService<T> extends IService<T> {
    List<T> search(String key);
}
