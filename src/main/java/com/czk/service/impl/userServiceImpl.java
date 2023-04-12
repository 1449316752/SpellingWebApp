package com.czk.service.impl;

import com.czk.dao.userDao;
import com.czk.domain.User;
import com.czk.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override
    public User selectUser(User user) {
        return userDao.selectUser(user);
    }
}
