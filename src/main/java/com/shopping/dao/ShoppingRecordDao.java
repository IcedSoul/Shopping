package com.shopping.dao;

import com.shopping.entity.ShoppingRecord;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingRecordDao {
    ShoppingRecord getShoppingRecord(int userId, int productId,String time);

    void addShoppingRecord(ShoppingRecord shoppingRecord);

    boolean deleteShoppingRecord(int userId,int productId);

    boolean updateShoppingRecord(ShoppingRecord shoppingRecord);

    List<ShoppingRecord> getShoppingRecords(int userId);

    List<ShoppingRecord> getAllShoppingRecords();

    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    boolean getUserProductRecord(int userId,int productId);

    boolean deleteShoppingRecordByUser(int userId);

    boolean deleteShoppingRecordByProductId(int productId);
}
