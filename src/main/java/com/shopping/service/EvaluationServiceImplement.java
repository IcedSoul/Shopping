package com.shopping.service;

import com.shopping.dao.EvaluationDao;
import com.shopping.entity.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 14437 on 2017/3/7.
 */
@Service
public class EvaluationServiceImplement implements EvaluationService {
    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public Evaluation getEvaluation(int userId, int productId, String time) {
        return evaluationDao.getEvaluation(userId,productId,time);
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        evaluationDao.addEvaluation(evaluation);
    }

    @Override
    public boolean deleteEvaluation(int userId, int productId, String time) {
        return evaluationDao.deleteEvaluation(userId,productId,time);
    }

    @Override
    public boolean updateEvaluation(Evaluation evaluation) {
        return evaluationDao.updateEvaluation(evaluation);
    }

    @Override
    public List<Evaluation> getProductEvaluation(int productId) {
        return evaluationDao.getProductEvaluation(productId);
    }
}
