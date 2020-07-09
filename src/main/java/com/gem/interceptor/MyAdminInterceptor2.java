package com.gem.interceptor;

import com.gem.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 员工登录验证拦截器
 * @Date: 2020-07-09
 **/
public class MyAdminInterceptor2 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return true;
        }
        response.sendRedirect("/crm/login");
        return false;
    }
}
