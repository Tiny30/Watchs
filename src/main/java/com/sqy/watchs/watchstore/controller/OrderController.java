package com.sqy.watchs.watchstore.controller;


import com.sqy.watchs.stream.HStream;
import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Order;

import com.sqy.watchs.watchstore.service.OrderService;
import com.sqy.watchs.watchstore.service.ProductService;
import com.sqy.watchs.watchstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends HoshiController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService ;

    /**
     * 填写订单，返回一个ID
     *
     * @param order
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(@RequestBody Order order) {
        return $(respData -> {
            order.setCreateTime(new Date());
            orderService.save(order);
            String orderID = order.getId();
            if (orderID == null && orderID.equals(" ")) {
                respData.success(false).msg("添加失败");
            } else {
                respData.success(true).data(orderID).msg("添加成功");
            }
        });
    }

    /**
     * 发货处理:先判断ID是个存在，在判断是否为管理员，管理员更改是否为发货还是未发货
     *
     * @param order
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(@RequestBody Order order) {
        return $(booleanRespData -> {
            orderService.updateById(order);
            booleanRespData.success(true).data(true).msg("更改成功");
        });
    }

    @DeleteMapping("/remove/{id}")
    public RespData<Boolean> remove(@PathVariable String id){
        return $(resp->{
            boolean b = orderService.removeById(id);
            if(b){
                resp.success(true).data(b).msg("删除成功") ;
            }
        });
    }

    /**
     * 根据订单号查询订单
     * @param num
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<Order>> list(@RequestParam(name = "search",required = false,defaultValue = "") String num,
                                        @RequestParam(name = "userId", required = false, defaultValue = "") String userId,
                                        @PathVariable int pageIndex, @PathVariable int pageSize){
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Order> orderList = orderService.listSearch(num, userId);
            HStream.$(orderList).forEach(order -> {
                order.setUser(userService.getById(order.getUserId()));
                order.setProduct(productService.getById(order.getProductId()));
            });
            listRespData.success(true).data(orderList).msg("查询成功");
        });
    }
}
