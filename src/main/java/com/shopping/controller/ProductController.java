package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.service.ProductService;
import com.shopping.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14437 on 2017/3/1.
 */
@Controller
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public Map<String,Object> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }

    //推荐写法
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public Response deleteProduct(int id) {
        return productService.deleteProduct(id);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(String name,String description,String keyWord,int price,int counts,int type) {
        System.out.println("添加了商品："+name);
        String result ="fail";
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setKeyWord(keyWord);
        product.setPrice(price);
        product.setCounts(counts);
        product.setType(type);
        productService.addProduct(product);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productDetail(int id, HttpSession httpSession) {
        System.out.println("I am here!"+id);
        Product product = productService.getProduct(id);
        httpSession.setAttribute("productDetail",product);
        System.out.print("I am here"+product.getName());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/product_detail")
    public String product_detail() {
        return "product_detail";
    }

    @RequestMapping(value = "/searchPre", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchPre(String searchKeyWord,HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord",searchKeyWord);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchProduct(String searchKeyWord){
        System.out.println("我到了SearchProduct"+searchKeyWord);
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getProductsByKeyWord(searchKeyWord);
        String searchResult = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",searchResult);
        System.out.println("我返回了"+searchResult);
        return resultMap;
    }

    @RequestMapping(value = "/getProductById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProductById(int id) {
        Product product = productService.getProduct(id);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile productImgUpload,String name, HttpServletRequest request) {
        String result = "fail";
        try{
            if(productImgUpload != null && !productImgUpload.isEmpty()) {
                String fileRealPath = request.getSession().getServletContext().getRealPath("/static/img");
                int id = productService.getProduct(name).getId();
                String fileName = String.valueOf(id)+".jpg";
                File fileFolder = new File(fileRealPath);
                System.out.println("fileRealPath=" + fileRealPath+"/"+fileName);
                if(!fileFolder.exists()){
                    fileFolder.mkdirs();
                }
                File file = new File(fileFolder,fileName);
                productImgUpload.transferTo(file);
                result = "success";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
