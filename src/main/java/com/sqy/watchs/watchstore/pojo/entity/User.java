package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    /**
     * 用户角色
     */
    public interface Role{
        String ADMIN = "admin" ;
        String USER = "user" ;
    }

    /**
     * 唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;

    /**
     * 用户名称
     */
    private String name ;

    /**
     * 用户账号 用于登录
     * */

    private String account ;

    /**
     * 用户密码
     */
    private String password ;

   /* *
     * 用户角色*/

    private String role = Role.USER;

    /*创建时间*/
    private Date createTime ;
   /* private  String description;*/
   /* *//**
     * 创建时间
     *//*/



    /*
    * 登录地址
    * */
    private String adress;
    /**
     * 用户登录凭证
     */
    private String token ;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Address> getAddressList(){
        List<Address>  addressList;
        try{
            addressList = new ObjectMapper().readValue(adress, new TypeReference<List<Address>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            addressList = new ArrayList<>() ;
        }
        return addressList ;
    }

    public void setAddressList(List<Address> addressList){
        try {
            adress = new ObjectMapper().writeValueAsString(addressList) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
