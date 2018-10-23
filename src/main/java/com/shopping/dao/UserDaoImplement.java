package com.shopping.dao;

import com.shopping.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/1.
 */
@Repository
public class UserDaoImplement implements UserDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public User getUser(int id) {
        String hql = "from User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (User)query.uniqueResult();
    }

    @Override
    public User getUser(String nameOrEmail) {
        String hql = "from User where email=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, nameOrEmail);
        if(query.uniqueResult() == null){
            hql = "from User where name=?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter(0, nameOrEmail);
        }
        return (User)query.uniqueResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        String hql = "delete User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateUser(User user) {
        String hql = "update User set name = ?,email=?,nickName=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,user.getName());
        query.setParameter(1,user.getEmail());
        query.setParameter(2,user.getNickName());
        query.setParameter(3,user.getId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<User> getAllUser() {
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
