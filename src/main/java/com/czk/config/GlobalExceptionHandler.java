package com.czk.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.czk.execption.UnauthorizedException;
import com.czk.tools.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof UnauthorizedException) {
            // 如果是未授权异常，则返回自定义的错误响应
            UnauthorizedException unauthorizedException = (UnauthorizedException) ex;
            Result result = unauthorizedException.getResult();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            try {
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ModelAndView();
        }
        // 其他异常情况，返回默认的500错误
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }
}
