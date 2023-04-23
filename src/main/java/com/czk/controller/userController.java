package com.czk.controller;

import com.czk.dao.userDao;
import com.czk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userDao userDao;

    /**
     * 登录，检查账号和密码
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpSession session, @RequestBody User user){
        User theUser = userDao.selectUser(user);
        if (theUser != null) {
            session.setAttribute("user",theUser);
            System.out.println(theUser.getU_id()+"登录了");
        }
        return new Result(theUser != null? Code.GET_OK : Code.GET_ERR ,theUser , theUser != null? "登录成功":"账号或密码错误");
    }


    @GetMapping("/isLogin")
    public Result home(@SessionAttribute(required = false) User user) {
        System.out.println(user);
        if (user != null) {
            return new Result(Code.GET_OK,user,"已登录");
        } else {
            return new Result(Code.GET_ERR,null,"未登录");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println(user.getU_id()+"logout");
        // 清除Session中的用户信息
        session.removeAttribute("user");
        return new Result(Code.GET_OK,null,"已登出");
    }
}
