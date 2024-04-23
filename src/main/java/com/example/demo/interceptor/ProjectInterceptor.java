package com.example.demo.interceptor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ComponentScan
@Service
public class ProjectInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("############### preHandle #################");
        System.out.println(request.getContentType());
        return true;
    }
}
