package com.shopping.entity;

import java.io.Serializable;

/**
 * Created by 14437 on 2017/3/3.
 */
public class ShoppingRecordPriKey implements Serializable {
    private int userId;
    private int productId;
    private String time;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingRecordPriKey)) return false;

        ShoppingRecordPriKey that = (ShoppingRecordPriKey) o;

        if (getUserId() != that.getUserId()) return false;
        if (getProductId() != that.getProductId()) return false;
        return getTime().equals(that.getTime());

    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + getProductId();
        result = 31 * result + getTime().hashCode();
        return result;
    }
}
