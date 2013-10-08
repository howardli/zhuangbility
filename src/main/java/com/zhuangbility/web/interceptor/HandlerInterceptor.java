/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhuangbility.bll.ZhuangbilityException;
import com.zhuangbility.bll.manager.PostManager;
import com.zhuangbility.bll.manager.TagManager;
import com.zhuangbility.common.ExceptionEnum;
import com.zhuangbility.model.Account;
import com.zhuangbility.web.annotation.Login;

/**
 * 
 * @author Howard.Li
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PostManager postManager;
    @Autowired
    private TagManager  tagManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Login login = handlerMethod.getMethodAnnotation(Login.class);
            if (login != null) {
                Account account = (Account) SecurityUtils.getSubject().getPrincipal();
                if (account == null) {
                    throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            modelAndView.addObject("isAdmin", SecurityUtils.getSubject().getPrincipal() != null);
            modelAndView.addObject("lastPosts", postManager.findAll(1).getContent());
            modelAndView.addObject("allTags", tagManager.findAll());
        }
    }
}
