package com.shopping.dao;

import com.shopping.entity.Evaluation;
import com.shopping.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/7.
 */
@Repository
public class EvaluationDaoImplement implements EvaluationDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Evaluation getEvaluation(int userId, int productId, String time) {
        String hql = "from Evaluation where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (Evaluation) query.uniqueResult();
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        sessionFactory.getCurrentSession().save(evaluation);
    }

    @Override
    public boolean deleteEvaluation(int userId, int productId, String time) {
        String hql = "delete Evaluation where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateEvaluation(Evaluation evaluation) {
        String hql = "update Evaluation set content=? where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, evaluation.getContent());
        query.setParameter(1, evaluation.getUserId());
        query.setParameter(2, evaluation.getProductId());
        query.setParameter(3, evaluation.getTime());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Evaluation> getProductEvaluation(int productId) {
        String hql = "from Evaluation where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return  query.list();
    }

    @Override
    public boolean deleteEvaluationByUser(int userId) {
        String hql = "delete Evaluation where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean deleteEvaluationByProduct(int productId) {
        String hql = "delete Evaluation where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return query.executeUpdate() > 0;
    }
}
