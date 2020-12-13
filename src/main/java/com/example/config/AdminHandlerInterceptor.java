package com.example.config;

import com.example.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户的session.
        //request.getSession().getAttribute 得到`loginUser`的session
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } else {
            if(loginUser.getRole() == 0) {
                request.setAttribute("msg", "非管理员没有权限访问后台，请先登录");
                request.getRequestDispatcher("/login").forward(request, response);
                return false;
            }
            return true;
        }
    }
}
