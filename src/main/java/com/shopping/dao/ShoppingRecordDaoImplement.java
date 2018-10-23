package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
@Repository
public class ShoppingRecordDaoImplement implements ShoppingRecordDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId,String time) {
        String hql = "from ShoppingRecord where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (ShoppingRecord) query.uniqueResult();
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        sessionFactory.getCurrentSession().save(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        String hql = "delete ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        String hql = "update ShoppingReocrd set orderStatus=? where userId=? and productId=? and time=?";
        String sql = "update shopping_record set order_status="+shoppingRecord.getOrderStatus()+" where user_id="+shoppingRecord.getUserId()+" and product_id="+shoppingRecord.getProductId()+" and time='"+shoppingRecord.getTime()+"'";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter(0,shoppingRecord.getOrderStatus());
//        query.setParameter(1,shoppingRecord.getUserId());
//        query.setParameter(2,shoppingRecord.getProductId());
//        query.setParameter(3,shoppingRecord.getTime());
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        String hql = "from ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        String hql = "from ShoppingRecord";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        String hql = "from ShoppingRecord where orderStatus=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,orderStatus);
        return query.list();
    }

    @Override
    public boolean getUserProductRecord(int userId,int productId) {
        String hql = "from ShoppingRecord where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        return query.list().size()>0;
    }

    @Override
    public boolean deleteShoppingRecordByUser(int userId) {
        String hql = "delete ShoppingRecord where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean deleteShoppingRecordByProductId(int productId) {
        String hql = "delete ShoppingRecord where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return query.executeUpdate() > 0;
    }
}
