package com.sqy.watchs.watchstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Disecern;
import com.sqy.watchs.watchstore.pojo.entity.Order;
import com.sqy.watchs.watchstore.service.DisecernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/disecern")
public class DisecernController extends HoshiController {
    @Autowired
    private DisecernService disecernService;

    /**
     * 填写鉴别单，根据返回的ID查看是否填写成功
     *
     * @param disecern
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(Disecern disecern) {
        return $(respData -> {
            disecernService.save(disecern);
            String disecernID = disecern.getId();
            if (disecernID == null && disecernID.equals(" ")) {
                respData.success(false).msg("添加失败");
            } else {
                respData.success(true).data(disecernID).msg("添加成功");
            }
        });
    }

    /**
     * 管理员更改鉴别单状态
     * @param disecern
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(Disecern disecern) {
        return $(booleanRespData -> {
            if (disecern.getUser().getId() != null) {
                if (disecern.getUser().getRole() != "admin") {
                    booleanRespData.success(false).msg("非管理员无法更改");
                } else {
                    Disecern disecernId = disecernService.getById(disecern.getId());
                    disecernId.setStatus(disecern.getStatus());
                    disecernService.updateById(disecernId);
                    booleanRespData.success(true).data(true).msg("更改成功");
                }
            } else {

                booleanRespData.success(false).data(false).msg("更改失败");
            }
        });
    }

    /**
     * 管理员和用户查看鉴别单的状态
     *
     * @param num
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public RespData<List<Disecern>> search(@RequestParam(name = "search", required = false, defaultValue = "")
                                                   String num, @PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            List<Disecern> disecernList = disecernService.search(num);
            listRespData.success(true).data(disecernList);

        });
    }
}
