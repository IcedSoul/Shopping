package com.shopping.service;

import com.shopping.entity.ShoppingRecord;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingRecordService {
    public ShoppingRecord getShoppingRecord(int userId, int productId,String time);

    public void addShoppingRecord(ShoppingRecord shoppingRecord);

    public boolean deleteShoppingRecord(int userId,int productId);

    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord);

    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    public List<ShoppingRecord> getShoppingRecords(int userId);

    public List<ShoppingRecord> getAllShoppingRecords();

    public boolean getUserProductRecord(int userId,int productId);
}
