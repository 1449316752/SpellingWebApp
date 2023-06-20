package com.czk.controller;

import com.czk.domain.User;
import com.czk.entity.AddUserType;
import com.czk.service.UserService;
import com.czk.tools.EmailUtil;
import com.czk.tools.Mail;
import com.czk.tools.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private EmailUtil emailUtil;
    /**
     * 登录，检查账号和密码
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpSession session, @RequestBody User user){
        User theUser = userService.selectUser(user);
        if (theUser != null) {
            String token= TokenUtils.sign(user);
            theUser.setToken(token);
            session.setAttribute("token",token);
            session.setAttribute("user",theUser);
            System.out.println(theUser.getUsername()+" 登录了 token "+token);
            System.out.println();
        }
        return new Result(theUser != null? Code.GET_OK : Code.GET_ERR ,theUser , theUser != null? "登录成功":"账号或密码错误");
    }


    @GetMapping("/isLogin")
    public Result home(@SessionAttribute(required = false) User user) {
        System.out.println(user);
        if (user != null) {
            return Result.Success(user);
        } else {
            return Result.Error("未登录");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUsername()+"logout");
        // 清除Session中的用户信息
        session.removeAttribute("user");
        session.removeAttribute("token");
        return new Result(Code.GET_OK,null,"已登出");
    }

    @Value("${properties.basePath}")
    private String basePath;
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        //file是一个临时文件，需要转存
        String originFileName = file.getOriginalFilename();//获取原始文件名
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));//截取文件后缀
        //最好使用UUID生成文件名，避免重复导致覆盖
        String fileName = UUID.randomUUID().toString()  + suffix;
        //判断路是否存在
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //开始转存
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.Success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        if(name==null || "".equals(name)){
            name = "img.png";
        }
        try {//输入流，读取文件
            FileInputStream inputStream = new FileInputStream(basePath + name);
            //输出流，传出文件到浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getCode")
    public Result getCode(@RequestBody AddUserType addUserType, HttpSession httpSession) {

        //获取邮箱
        String email = addUserType.getEmail();
        if (httpSession.getAttribute(email) != null){
            return Result.Error("验证码已发送，请误重复操作");
        }
        if (StringUtils.isNotEmpty(email)) {
            //生成随机验证码
            String code = String.valueOf((int)(Math.random()*9000+1000));
            log.info("The email={}", email);

            //改用qq邮箱
            Mail mail = new Mail();
            String userEmail = "1449316752@qq.com";
            mail.setRecipient(email);
            mail.setSubject("注册系统");
            mail.setContent("你好，您正在拼写练习网注册账号，以下是您的登录验证码："+code+"  请在十分钟内使用");
            try {
                emailUtil.sendSimpleMail(mail);
            } catch (Exception e) {
                return Result.Error("发送失败");
            }

            //将生成的验证码保存到Session，改用Redis保存
            System.out.println("已发送验证码到:"+email);
            httpSession.setAttribute(email, code);

            //生成的验证码保存到redis，设置5分钟超时
            //redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.Success("验证码发送成功");
        }
        return Result.Error("发送失败");
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserType addUser, HttpSession session){
        System.out.println(addUser);
        //注册的各项验证
        String code = (String) session.getAttribute(addUser.getEmail());
        System.out.println("code = "+code);
        if (code == null){
            return Result.Error("请先获取验证码");
        }
        if (!code.equals(addUser.getCode())){
            return Result.Error("验证码错误");
        }
        if (!addUser.getPassword().equals(addUser.getRePassword())){
            return Result.Error("密码不一致");
        }
        User user = userService.selectUserByEmail(addUser.getEmail());
        if (user != null){
            return Result.Error("该邮箱已被注册");
        }
        user = userService.selectUserByUserName(addUser.getUsername());
        if (user != null){
            return Result.Error("该用户名已被注册");
        }
        boolean b = userService.addUser(addUser.getUsername(), addUser.getPassword(), addUser.getEmail());
        if (b){
            return Result.Success();
        }
        return Result.Error("未知错误");
    }

    @PutMapping("/chaneImg/{newImgName}")
    public Result chaneImg(@PathVariable String newImgName,@RequestBody User user){
        user.setHeadPath(newImgName);
        boolean flag = userService.upDataUserHeadPath(user);
        if (flag){
            return Result.Success(user,"更换头像成功");
        }
        return Result.Error("更换失败，也可能是你乱搞");
    }

        @PutMapping("/changePassword")
    public Result changePassword(@RequestBody AddUserType addUser){
        System.out.println(addUser);
        //注册的各项验证
        if (!addUser.getPassword().equals(addUser.getRePassword())){
            return Result.Error("密码不一致");
        }
        boolean b = userService.changePassword(addUser.getUsername(), addUser.getPassword());
        if (b){
            return Result.Success(userService.selectUserByUserName(addUser.getUsername()));
        }
        return Result.Error("未知错误");
    }
}
