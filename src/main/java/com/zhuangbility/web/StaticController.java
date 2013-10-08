/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 *
 * @author HowardLi
 */
@Controller
public class StaticController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "/static/about";
    }
}
