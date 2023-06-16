package com.czk.interceptor;


import com.czk.tools.TokenUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = (String)request.getSession().getAttribute("token");
        //String token = request.getHeader("token");
        System.out.println("token::"+token);
        if (token!=null){
            boolean result= TokenUtils.verify(token);
            if (result){
                System.out.println("请求："+request.getRequestURI()+" 通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json=new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","500");
            response.sendRedirect(request.getContextPath()+"/login.html");
            response.getWriter().append(json.toString());
            System.out.println("请求："+request.getRequestURI()+" 认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        /**
         * 还可以在此处检验用户存不存在等操作
         */
        return false;
    }
}