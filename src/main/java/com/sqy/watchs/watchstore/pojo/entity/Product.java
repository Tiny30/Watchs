package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Product {
    /**
     * 文档id
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
    private Integer price;

    /*
    * 手表描述/介绍
    * */
    private String introduction;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
