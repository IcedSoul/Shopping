package com.shopping.entity;

import javax.persistence.*;

/**
 * Created by 14437 on 2017/3/3.
 */
@Entity
@Table(name="shopping_record")
@IdClass(value=ShoppingRecordPriKey.class)
public class ShoppingRecord {
    private int userId;
    private int productId;
    private String time;
    private int orderStatus;
    private int productPrice;
    private int counts;

    @Id
    @Column(name="user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name="product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name="time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name="order_status")
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name="product_price")
    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Column(name="counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }


}
