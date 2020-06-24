package org.gec.controller.interceptor;

import org.gec.pojo.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的路径
        String uri = request.getRequestURI();

        //判断     !=-1表示找到含有login的链接
        if(uri.indexOf("login") != -1){
            return true;//放行
        }

        //获取session
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user_session");
        //判断
        if(user != null){
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
