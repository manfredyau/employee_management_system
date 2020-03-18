package com.yau.springboot03web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("loginMsg") != null) {
            return true;
        } else {
            request.setAttribute("msg", "沒有權限，請先登入");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
    }
}
