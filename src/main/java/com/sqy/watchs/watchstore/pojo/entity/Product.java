package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.watchs.watchstore.json.SpDateDeSerializer;
import com.sqy.watchs.watchstore.json.SpDateSerializer;

import java.util.Date;

public class Product {
    /**
     * 商品id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /*
    * 手表名称
    * */
    private String name;
    /*
    * 价格
    * */
    private Double price;
    /**
     * 商品图片
     */
    private String img;

    /**
     * 手表描述/介绍
     * */
    private String introduction;

    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date createTime ;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
