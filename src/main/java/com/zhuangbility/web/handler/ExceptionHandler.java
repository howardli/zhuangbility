/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zhuangbility.bll.ZhuangbilityException;

/**
 * 
 * @author Howard.Li
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (ex instanceof ZhuangbilityException) {
            ZhuangbilityException exception = (ZhuangbilityException) ex;
            Map<String, String> model = new HashMap<String, String>();
            model.put("error", exception.getExceptionEnum().getMessage());
            return new ModelAndView("error/error", model);
        } else {
            return new ModelAndView("error/500");
        }
    }
}
