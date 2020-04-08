package com.sqy.watchs.watchstore.controller;

import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.User;
import com.sqy.watchs.watchstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController extends HoshiController {
    @Autowired
    private UserService userService;


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(User user) {
        return $(stringRespData -> {
            userService.save(user);
            String id = user.getId();
            if (id == null && id.equals("")) {
                RespData.succeed(false).msg("添加失败");
            } else {
                RespData.succeed(true).data(true).msg("添加成功");
            }
        });
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del")
    public RespData<Boolean> del(String id) {
        return $(booleanRespData -> {
            if (id == null) {
                booleanRespData.success(false).msg("未发现该ID");
            } else {
                userService.removeById(id);
                booleanRespData.success(true).data(true).msg("已删除");
            }
        });
    }

    /**
     * 根据id更改密码
     *
     * @param user
     * @return
     */
    @PutMapping("/updatePwd")
    public RespData<Boolean> updatePwd(User user) {
        return $(booleanRespData -> {
            if (user.getId() == null && user.getId().equals(" ")) {
                RespData.succeed(false).msg("未找到更新ID");
            } else {
                User byID = userService.getById(user.getId());
                byID.setPassword(user.getPassword());
                userService.updateById(byID);
                RespData.succeed(false).data(true).msg("更新成功");
            }
        });
    }

    /**
     * 地址管理：更改地址
     *
     * @param user,,..
     * @return
     */
    public RespData<Boolean> adress(User user) {
        return $(booleanRespData -> {
            if (user.getId() == null && user.getId().equals(" ")) {
                RespData.succeed(false).msg("未找到更新ID");
            } else {
                User byID = userService.getById(user.getId());
                byID.setAdress(user.getAdress());
                userService.updateById(byID);
                RespData.succeed(false).data(true).msg("更新成功");
            }
        });
    }


    /***
     * 根据关键字(姓名)进行查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @author：SQY
     * @Date:2020.4.8
     */
    @GetMapping("/listBySearch/{pageIndex}/{pageSize}")
    public RespData<List<User>> listBySearch(@RequestParam(name = "search", required = false, defaultValue = "") String key,
                                             @PathVariable int pageIndex, @PathVariable int pageSize) {

        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<User> userList = userService.listBySearch(key);
            RespData.succeed(true).msg("查询成功！");
        });
    }

}
