package com.sqy.watchs.watchstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.watchs.watchstore.pojo.entity.Disecern;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface DisecernService extends IService<Disecern> {
    List<Disecern> search(String key);
}
