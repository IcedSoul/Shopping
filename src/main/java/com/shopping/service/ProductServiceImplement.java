package com.shopping.service;

import com.shopping.dao.EvaluationDao;
import com.shopping.dao.ProductDao;
import com.shopping.dao.ShoppingCarDao;
import com.shopping.dao.ShoppingRecordDao;
import com.shopping.entity.Product;
import com.shopping.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 14437 on 2017/3/2.
 */

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    @Transactional
    public Response deleteProduct(int id) {
        try {
            evaluationDao.deleteEvaluationByProduct(id);
            shoppingCarDao.deleteShoppingCarByProduct(id);
            shoppingRecordDao.deleteShoppingRecordByProductId(id);
            productDao.deleteProduct(id);
            return new Response(1, "删除商品成功", null);
        }catch (Exception e){
            return new Response(0,"删除商品失败",null);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
