package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Order {

    public interface Status{
        String FUTURE = "future" ;
        String PAST = "past" ;
        String NOW = "now";
    }

     /**
     * 订单id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /*
    * 订单号码
    * */
    private Integer num;
    /*
    * 订单名称
    * */
    private  String name;
    /*
    * 订单描述
    * */
    private  String description;
    /*
    * 创建时间
    * */
    private Date createTime ;
    /**
     * 发货状态，初始化为未发货
     *
     */
    private String  status = Status.PAST;

    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
