package com.sqy.watchs.watchstore.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.watchs.watchstore.json.SpDateDeSerializer;
import com.sqy.watchs.watchstore.json.SpDateSerializer;

import java.util.Date;

@TableName("w_order")
public class Order {

    public interface Status{
        String FUTURE = "future" ;//未发货
        String PAST = "past" ;//已发货
        String NOW = "now";//正发货
    }

     /**
     * 订单id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /*
    * 订单号码
    * */
    private String num;
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
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date createTime ;
    /**
     * 发货状态，初始化为未发货
     *
     */
    private Boolean status = false;

    private String userId ;
    @TableField(exist = false)
    private User user;
    private String addressId ;

    private String productId;
    @TableField(exist = false)
    private Product product ;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
