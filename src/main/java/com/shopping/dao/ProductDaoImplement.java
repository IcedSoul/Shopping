package com.shopping.dao;

import com.shopping.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/1.
 */
@Repository
public class ProductDaoImplement implements ProductDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(int id) {
        String hql = "from Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Product) query.uniqueResult();
    }

    @Override
    public Product getProduct(String name) {
        String hql = "from Product where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,name);
        return (Product) query.uniqueResult();
    }

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        String hql = "delete Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        String hql = "update Product set name=?,description=?,keyWord=?,price=?,counts=?,type=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,product.getName());
        query.setParameter(1,product.getDescription());
        query.setParameter(2,product.getKeyWord());
        query.setParameter(3,product.getPrice());
        query.setParameter(4,product.getCounts());
        query.setParameter(5,product.getType());
        query.setParameter(6,product.getId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        String queryKeyWord = "%";
        for(int i=0;i<searchKeyWord.length();i++){
            queryKeyWord += String.valueOf(searchKeyWord.charAt(i)) +"%";
        }
        System.out.println("我搜索了"+queryKeyWord);
        String hql = "from Product where name like ? or key_word like ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,queryKeyWord);
        query.setParameter(1,queryKeyWord);
        return query.list();
    }

    @Override
    public List<Product> getProductsByType(int type) {
        String hql = "from Product where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();
    }


    @Override
    public List<Product> getAllProduct() {
        String hql = "from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
