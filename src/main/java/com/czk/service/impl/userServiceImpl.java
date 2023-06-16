package com.czk.service.impl;

import com.czk.dao.UserDao;
import com.czk.domain.User;
import com.czk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectUser(User user) {
        return userDao.selectUser(user);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public User selectUserByUserName(String username) {
        return userDao.selectUserByUserName(username);
    }

    @Override
    public boolean addUser(String username, String password, String email) {
        return userDao.addUser(username,password,email)==1;
    }

    @Override
    public boolean upDataUserHeadPath(User user) {
        return userDao.upDataUserHeadPath(user) == 1;
    }

    @Override
    public boolean changePassword(String username, String password) {
        return userDao.changePassword(username,password) == 1;
    }
}
