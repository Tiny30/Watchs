package com.sqy.watchs.watchstore.controller;


import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Order;

import com.sqy.watchs.watchstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends HoshiController {
    @Autowired
    private OrderService orderService;

    /**
     * 填写订单，返回一个ID
     *
     * @param order
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(Order order) {
        return $(respData -> {
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
    @PutMapping("/send")
    public RespData<Boolean> send(Order order) {
        return $(booleanRespData -> {
            if (order.getUser().getId() != null) {
                if (order.getUser().getRole() != "admin") {
                    booleanRespData.success(false).msg("非管理员无法更改");
                } else {
                    Order orderId = orderService.getById(order.getId());
                    orderId.setStatus(order.getStatus());
                    orderService.updateById(orderId);
                    booleanRespData.success(true).data(true).msg("更改失败");
                }
            } else {

                booleanRespData.success(false).data(false).msg("更改失败");
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
    @GetMapping("/search")
    public RespData<List<Order>> search(@RequestParam(name = "search",required = false,defaultValue = "") String num,
                                        @PathVariable int pageIndex, @PathVariable int pageSize){
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Order> orderList = orderService.listSearch(num);
            listRespData.success(true).data(orderList);
        });
    }
}
