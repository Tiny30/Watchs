package com.sqy.watchs.watchstore.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqy.watchs.stream.HStream;
import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Discern;
import com.sqy.watchs.watchstore.service.IHoshiService;
import com.sqy.watchs.watchstore.service.impl.HoshiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public abstract class SPController<T, S extends IHoshiService<T>> extends HoshiController {

    @Autowired
    S coreService ;

    @DeleteMapping("/remove/{id}")
    public RespData<Boolean> remove(@PathVariable String id){
        return $(resp->{
            coreService.removeById(id) ;
            resp.success(true).data(true).msg("删除成功") ;
        });
    }

    @PutMapping("/update")
    public RespData<Boolean> update(@RequestBody T entity){
        return $(resp->{
            coreService.updateById(entity) ;
            resp.success(true).data(true).msg("更新成功") ;
        });
    }

    @PostMapping("/add")
    public RespData<Serializable> add(@RequestBody T entity){
        return $(resp->{
            coreService.save(entity);
            resp.success(true).data(getId(entity)).msg("保存成功") ;
        });
    }
    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<T>> list(@RequestParam(name = "search", required = false, defaultValue = "")
                                                String num, @PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<T> list = coreService.search(num);
            onSearchFinish(list);
            listRespData.success(true).data(list).msg("查询完成");
        });
    }

    protected void onSearchFinish(List<T> list){

    }

    protected  Serializable getId(T entity){
        try {
            Field id = entity.getClass().getDeclaredField("id");
            id.setAccessible(true);
            return (Serializable) id.get(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
