/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhuangbility.bll.manager.PostManager;
import com.zhuangbility.model.Post;

/**
 * 首页相关控制器类
 *
 * @author Howard.Li
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private PostManager postManager;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return index(1, model);
    }

    @RequestMapping(value = "/page/{pageNo}", method = RequestMethod.GET)
    public String index(@PathVariable("pageNo") int pageNo, Model model) {
        Page<Post> postPage = postManager.findAll(pageNo);
        model.addAttribute("postPage", postPage);
        return "/post/index";
    }
}
