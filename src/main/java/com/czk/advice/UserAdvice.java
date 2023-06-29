package com.czk.advice;

import com.czk.domain.User;
import com.czk.entity.AddUserType;
import com.czk.execption.UnauthorizedException;
import com.czk.tools.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.czk.domain.User;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Component
@Aspect
public class UserAdvice {

    //修改密码的切入点
    @Pointcut("execution(* com.czk.controller.UserController.changePassword(..))")
    public void changePasswordPointCut(){}

    //修改头像的切入点
    @Pointcut("execution(* com.czk.controller.UserController.chaneImg(..))")
    public void changeImgPointCut(){}

    /**
     * 修改密码的环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("changePasswordPointCut()")
    public Object around1(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        AddUserType addUser = (AddUserType)point.getArgs()[0];
        System.out.println("方法参数的 addUser"+ addUser);

        //获取RequestSession
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        User user = (User) session.getAttribute("user");
        System.out.println("session的 user"+user);

        //验证
        if(addUser.getUsername().equals(user.getUsername())){
            System.out.println("验证一致！");
            return point.proceed();
        }
        throw new UnauthorizedException(Result.Error("无权修改"));
    }

    /**
     * 修改密码的环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("changeImgPointCut()")
    public Object around2(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        User user = (User) point.getArgs()[0];
        System.out.println("方法参数的 user"+ user);

        //获取RequestSession
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        User sessionUser = (User) session.getAttribute("user");
        System.out.println("session的 user"+user);

        //验证
        if(user.getUsername().equals(sessionUser.getUsername())){
            System.out.println("验证一致！");
            return point.proceed();
        }
        throw new UnauthorizedException(Result.Error("无权修改"));
    }
}
