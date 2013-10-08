/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登陆相关控制器类
 *
 * @author Howard.Li
 */
@Controller
@RequestMapping(value = "/login")
public class SessionController {

    @RequestMapping(method = RequestMethod.GET)
    public String _new() {
        return "session/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createFail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
            HttpServletRequest request, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        return "session/login";
    }
}
