package com.example.protal.interceptor;

import com.example.protal.util.HttpUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie c : cookies) {
            if (c.getName().equals("isLogin")) {
                String token = c.getValue();
                String data = HttpUtil.getData("http://localhost:8083/tokenIsHad/"+token);
                System.out.println(data);
                if(data.equals("1") ){
                    System.out.println("--------------------------------");
                    return true;
                }
            }
        }
        httpServletResponse.sendRedirect("http://localhost:8083/toLogin?returnurl=http://localhost:8082/cart/lookcart");
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
