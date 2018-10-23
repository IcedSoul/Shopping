package com.shopping.dao;

import com.shopping.entity.Product;

import java.util.List;

/**
 * Created by 14437 on 2017/3/1.
 */
public interface ProductDao {
    public Product getProduct(int id);

    public Product getProduct(String name);

    public void addProduct(Product product);

    public boolean deleteProduct(int id);

    public boolean updateProduct(Product product);

    public List<Product> getProductsByKeyWord(String searchKeyWord);

    public List<Product> getProductsByType(int type);

    public List<Product> getAllProduct();
}
