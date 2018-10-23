package com.shopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Evaluation;
import com.shopping.service.EvaluationService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14437 on 2017/3/7.
 */
@Controller
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;

    @Resource
    private ShoppingRecordService shoppingRecordService;

    @RequestMapping(value = "/addShoppingEvaluation",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoppingEvaluation(int userId, int productId, String content){
        System.out.println("我添加了"+userId+" "+productId);
        String result = null;
        if(shoppingRecordService.getUserProductRecord(userId,productId)){
            Evaluation evaluation = new Evaluation();
            evaluation.setUserId(userId);
            evaluation.setProductId(productId);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            evaluation.setTime(sf.format(date));
            evaluation.setContent(content);
            evaluationService.addEvaluation(evaluation);
            result = "success";
        }
        else{
            result="noneRecord";
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingEvaluations",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingEvaluations(int productId){
        List<Evaluation> evaluationList = evaluationService.getProductEvaluation(productId);
        String evaluations = JSONArray.toJSONString(evaluationList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",evaluations);
        return resultMap;
    }
}
