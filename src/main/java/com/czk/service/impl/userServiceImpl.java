package com.czk.service.impl;

import com.czk.dao.userDao;
import com.czk.domain.User;
import com.czk.service.userService;
import org.springframework.beans.factory.annotation.Autowired;

public class userServiceImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override
    public User selectUser(User user) {
        System.out.println("开始登录");
        return userDao.selectUser(user);
    }
}
