package com.shopping.service;

import com.shopping.entity.User;
import com.shopping.utils.Response;

import java.util.List;

/**
 * Created by 14437 on 2017/3/1.
 */
public interface UserService {
    User getUser(int id);

    User getUser(String nameOrEmail);

    void addUser(User user);

    //推荐写法
    Response deleteUser(int id);

    boolean updateUser(User user);

    List<User> getAllUser();
}
