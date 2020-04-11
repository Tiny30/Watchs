package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.watchs.watchstore.json.SpDateDeSerializer;
import com.sqy.watchs.watchstore.json.SpDateSerializer;

import java.io.Serializable;
import java.util.Date;

public class Discern {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 鉴别状态
     */
    private Boolean status = false;
    /*
     * 鉴别单号码
     * */
    private String num;
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
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date createTime ;
    /**
     * 鉴别图片
     */
    private String img ;

    /**
     * 上传的用户ID
     */
    private String userId;
    @TableField(exist = false)
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
