package com.czk.advice;

import com.czk.domain.User;
import com.czk.execption.UnauthorizedException;
import com.czk.tools.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

//WordRecordController的增强
@Component
@Aspect
public class WordRecordAdvice {
    //在WordListController中的带User参数的修改请求的切入点
    @Pointcut("execution(* com.czk.controller.WordListController.setWordRecord(..)) || execution(* com.czk.controller.WordListController.setRecordIsgrasp(..)) || execution(* com.czk.controller.WordListController.setRecordCount(..))")
    public void withUserPointCut(){}

    /**
     * 验证 修改 的是否为用户本人的数据
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("withUserPointCut()")
    public Object around1(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        User user = (User)point.getArgs()[0];
        System.out.println("方法参数的 user:  "+ user);

        //获取RequestSession
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        User sessionUser = (User) session.getAttribute("user");
        System.out.println("session的 user:  "+sessionUser);

        //验证
        if(user.getU_id().equals(sessionUser.getU_id())){
            System.out.println("验证一致！");
            return point.proceed();
        }
        System.out.println("不一致");
        throw new UnauthorizedException(Result.Error("无权修改"));
    }

    //在WordListController中带u_id参数的方法的切入点
    @Pointcut("execution(* com.czk.controller.WordListController.getWords(..)) || execution(* com.czk.controller.WordListController.getForgetWords(..)))")
    public void withU_idPointCut(){}

    /**
     * 验证 获取 的是否为用户本人的数据
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("withU_idPointCut()")
    public Object around2(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        int u_id = (int)point.getArgs()[0];
        System.out.println("方法参数的 u_id:  "+ u_id);

        //获取RequestSession
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        User sessionUser = (User) session.getAttribute("user");
        System.out.println("session的 user:  "+sessionUser);

        //验证
        if(String.valueOf(u_id).equals(sessionUser.getU_id())){
            System.out.println("验证一致！");
            return point.proceed();
        }
        System.out.println("不一致");
        throw new UnauthorizedException(Result.Error("无权获取"));
    }
}
