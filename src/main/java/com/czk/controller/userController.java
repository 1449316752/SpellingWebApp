package com.czk.controller;

import com.czk.dao.userDao;
import com.czk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userDao userDao;

    @PostMapping
    public Result login(@RequestBody User user){
        User theUser = userDao.selectUser(user);

        return new Result(theUser != null? Code.GET_OK : Code.GET_ERR ,theUser , "信息");
    }

}
