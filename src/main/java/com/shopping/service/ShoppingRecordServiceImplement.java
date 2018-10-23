package com.shopping.service;

import com.shopping.dao.ShoppingRecordDao;
import com.shopping.entity.ShoppingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
@Service
public class ShoppingRecordServiceImplement implements ShoppingRecordService {
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId,String time) {
        return shoppingRecordDao.getShoppingRecord(userId,productId,time);
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        shoppingRecordDao.addShoppingRecord(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId,productId);
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        return shoppingRecordDao.updateShoppingRecord(shoppingRecord);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        return shoppingRecordDao.getShoppingRecordsByOrderStatus(orderStatus);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        return shoppingRecordDao.getShoppingRecords(userId);
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        return shoppingRecordDao.getAllShoppingRecords();
    }

    @Override
    public boolean getUserProductRecord(int userId,int productId) {
        return shoppingRecordDao.getUserProductRecord(userId,productId);
    }
}
