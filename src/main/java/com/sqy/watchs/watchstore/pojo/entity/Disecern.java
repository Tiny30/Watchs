package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Disecern {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 鉴别状态
     */
    public interface Status{
        String ALREADY = "already" ;//已鉴别
        String NO = "no" ;//未鉴别
        String NOW = "now";//正在鉴别
    }
    private String status = Status.NO;
    /*
     * 鉴别单号码
     * */
    private Integer num;
    /*
     * 鉴别单名称
     * */
    private  String name;
    /*
     * 鉴别单描述
     * */
    private  String description;
    /*
     * 鉴别单创建时间
     * */
    private Date createTime ;
    private User user;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
