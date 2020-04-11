package com.sqy.watchs.watchstore.controller;


import com.sqy.watchs.stream.HStream;
import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Discern;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import com.sqy.watchs.watchstore.service.DisecernService;
import com.sqy.watchs.watchstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discern")
public class DisecernController extends SPController<Discern, DisecernService> {
    @Autowired
    private UserService userService ;

    @Override
    protected void onSearchFinish(List<Discern> list) {
        super.onSearchFinish(list);
        HStream.$(list).forEach(discern -> {
            discern.setUser(userService.getById(discern.getUserId()));
        });
    }

    /**
     * 根据订单号查询订单
     * @param num
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/listForUser/{pageIndex}/{pageSize}")
    public RespData<List<Discern>> listForUser(@RequestParam(name = "search",required = false,defaultValue = "") String num,
                                      @RequestParam(name = "userId", required = false, defaultValue = "") String userId,
                                      @PathVariable int pageIndex, @PathVariable int pageSize){
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Discern> list = coreService.listSearchByUserId(num, userId);
            HStream.$(list).forEach(order -> {
                order.setUser(userService.getById(order.getUserId()));
            });
            listRespData.success(true).data(list).msg("查询成功");
        });
    }
}
