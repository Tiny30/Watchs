package com.sqy.watchs.watchstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqy.watchs.stream.HStream;
import com.sqy.watchs.watchstore.author.TokenGenerator;
import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Address;
import com.sqy.watchs.watchstore.pojo.entity.User;
import com.sqy.watchs.watchstore.pojo.entity.UserPassword;
import com.sqy.watchs.watchstore.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
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
        return $(respData -> {
            userService.save(user);
            String id = user.getId();
            if (id == null && id.equals("")) {
                respData.success(false).data(id).msg("添加失败");

            } else {
                respData.success(true).data(id).msg("添加成功");

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
    @PutMapping("/password/update")
    public RespData<Boolean> updatePwd(@RequestBody UserPassword user) {
        return $(booleanRespData -> {
            if (user.getUserId() == null && user.getUserId().equals(" ")) {
                booleanRespData.success(false).msg("未找到用户");
            } else {
                //根据ID查询的用户
                User byID = userService.getById(user.getUserId());
                //加密
                String pwd = user.getOriginPassword();
                String oriPwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
                if(oriPwd.equals(byID.getPassword())){
                    if(Objects.equals(user.getNewPassword(), user.getConfirmPassword())){
                        byID.setPassword(DigestUtils.md5DigestAsHex(user.getNewPassword().getBytes()));
                        userService.updateById(byID);
                        booleanRespData.success(true).data(true).msg("修改成功") ;
                    }else {
                        booleanRespData.success(false).data(false).msg("两次密码不一致");
                    }
                }else {
                    booleanRespData.success(false).data(false).msg("原密码错误") ;
                }
            }
        });
    }

    /**
     * 地址管理：更改地址
     *
     * @return
     */
    @PutMapping("/address/update")
    public RespData<List<Address>> updateAddress(@RequestBody User user) {
        return $(booleanRespData -> {
            if (user.getId() == null && user.getId().equals(" ")) {
                booleanRespData.success(false).msg("未找到更新ID");
            } else {
                User byID = userService.getById(user.getId());
                List<Address> addressList = user.getAddressList();
                HStream.$(addressList).forEach(address -> {
                    if(address.getId() == null){
                        address.setId(UUID.randomUUID().toString());
                    }
                    if(address.getUserId() == null){
                        address.setUserId(user.getId());
                    }
                });
                user.setAddressList(addressList);
                byID.setAdress(user.getAdress());
                userService.updateById(byID);
                booleanRespData.success(true).data(addressList).msg("保存");
            }
        });
    }

    @GetMapping("/address/list/{userId}")
    public RespData<List<Address>> addressList(@PathVariable String userId){
        return $(resp->{
            User byId = userService.getById(userId);
            if(byId != null){
                String adress = byId.getAdress();

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Address> addressList = null;
                    if(adress==null || adress.isEmpty()){
                        addressList = new ArrayList<>() ;
                    }else {
                        addressList = objectMapper.readValue(adress, new TypeReference<List<Address>>() {});
                    }
                    resp.success(true).data(addressList).msg("查询成功");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
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
            listRespData.success(true).data(userList);
        });
    }

}
