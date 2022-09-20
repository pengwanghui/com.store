package com.hui.store.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**  检测全局session对象中是否有uid，如果有则放行，没有则重定向到登录页面
* @Param: 请求对象，响应对象 处理器（url+controller：映射）
* @return: 如果返回值为true表示放行当前的请求，如果返回值为false则表示拦截
* @Author: Pwh
* @Date: 2022/9/10
*/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if(obj ==null){
            response.sendRedirect(" /web/login.html");

            return false;
        }

        return true;

    }
}
