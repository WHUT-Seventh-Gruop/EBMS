package com.whut.seven.interceptor;

import com.whut.seven.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aaaaaaa
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // 未登录
            request.setAttribute("message","没有权限操作，请先登录！");
            String servletPath = request.getServletPath();
            //访问路径中存在admin字段
            if(servletPath.contains("admin")){
                request.getRequestDispatcher(request.getContextPath() + "/admin-login.html").forward(request,response);
                return false;
            }else{
                request.getRequestDispatcher(request.getContextPath() + "/login.html").forward(request,response);
                return false;
            }
        }else {
            // 登录成功
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
