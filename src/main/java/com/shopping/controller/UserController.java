package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.User;
import com.shopping.entity.UserDetail;
import com.shopping.service.UserDetailService;
import com.shopping.service.UserService;
import com.shopping.utils.Response;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 14437 on 2017/3/1.
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserDetailService userDetailService;


    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/amend_info")
    public String amend_info() {
        return "amend_info";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/control")
    public String control() {
        return "control";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String userNameOrEmail, String password, HttpSession httpSession) {
        System.out.println("我接收到了登录请求" + userNameOrEmail + " " + password);
        String result = "fail";
        User user = userService.getUser(userNameOrEmail);
        if (user == null)
            result = "unexist";
        else {
            UserDetail userDetail = userDetailService.getUserDetail(user.getId());
            if (userDetail.getPassword().equals(password)) {
                result = "success";
                httpSession.setAttribute("currentUser", user);
            } else
                result = "wrong";
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String email, String nickName, String password, String phoneNumber, int sex, String birthday, String postNumber, String address) {

        String result = "fail";
        User user = userService.getUser(userName);
        if (user != null) {
            result = "nameExist";
        } else {
            user = userService.getUser(email);
            if (user != null)
                result = "emailExist";
            else {
                User user1 = new User();
                user1.setName(userName);
                System.out.println(userName);
                user1.setEmail(email);
                System.out.println(email);
                user1.setNickName(nickName);
                System.out.println(nickName);
                user1.setRole(0);
                userService.addUser(user1);
                user1 = userService.getUser(userName);
                UserDetail userDetail = new UserDetail();
                userDetail.setId(user1.getId());
                userDetail.setAddress(address);
                userDetail.setBirthday(birthday);
                userDetail.setPassword(password);
                userDetail.setPhoneNumber(phoneNumber);
                userDetail.setSex(sex);
                userDetail.setPostNumber(postNumber);
                Date date = new Date();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userDetail.setRegisterTime(sf.format(date));
                userDetailService.addUserDetail(userDetail);
                result = "success";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(String userName, String email, String nickName, String password, String phoneNumber, int sex, String birthday, String postNumber, String address) {
        String result = "fail";
        User user = userService.getUser(userName);
        user.setEmail(email);
        user.setNickName(nickName);
        userService.updateUser(user);
        UserDetail userDetail = userDetailService.getUserDetail(user.getId());
        userDetail.setAddress(address);
        userDetail.setBirthday(birthday);
        userDetail.setPassword(password);
        userDetail.setPhoneNumber(phoneNumber);
        userDetail.setSex(sex);
        userDetail.setPostNumber(postNumber);
        userDetailService.updateUserDetail(userDetail);
        result = "success";
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllUser() {
//        System.out.println("我接收到了获取用户请求");
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
//        System.out.println("我返回的结果是"+allUsers);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }

    //2018.04.08 修改BUG 这种方法为前后端交互推荐写法
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Response deleteUser(int id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/getUserAddressAndPhoneNumber", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserAddressAndPhoneNumber(int id) {
        String address = userDetailService.getUserDetail(id).getAddress();
        String phoneNumber = userDetailService.getUserDetail(id).getPhoneNumber();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("address",address);
        resultMap.put("phoneNumber",phoneNumber);
        return resultMap;
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        httpSession.setAttribute("currentUser","");
        return "redirect:login";
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserById(int id) {
        User user = userService.getUser(id);
        String result = JSON.toJSONString(user);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getUserDetailById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserDetailById(int id) {
        UserDetail userDetail = userDetailService.getUserDetail(id);
        String result = JSON.toJSONString(userDetail);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

}
