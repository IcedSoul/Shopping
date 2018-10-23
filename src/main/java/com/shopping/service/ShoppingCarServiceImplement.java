package com.shopping.service;

import com.shopping.dao.ShoppingCarDao;
import com.shopping.entity.ShoppingCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
@Service
public class ShoppingCarServiceImplement implements ShoppingCarService {
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Override
    public ShoppingCar getShoppingCar(int userId, int productId) {
        return shoppingCarDao.getShoppingCar(userId,productId);
    }

    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        shoppingCarDao.addShoppingCar(shoppingCar);
    }

    @Override
    public boolean deleteShoppingCar(int userId, int productId) {
        return shoppingCarDao.deleteShoppingCar(userId,productId);
    }

    @Override
    public boolean updateShoppingCar(ShoppingCar shoppingCar) {
        return shoppingCarDao.updateShoppingCar(shoppingCar);
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        return shoppingCarDao.getShoppingCars(userId);
    }
}
