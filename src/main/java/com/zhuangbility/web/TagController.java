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

import com.zhuangbility.bll.manager.TagManager;
import com.zhuangbility.model.PostTag;
import com.zhuangbility.model.Tag;

/**
 * 
 *
 * @author HowardLi
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String show(@PathVariable("name") String name, Model model) {
        return show(name, 1, model);
    }

    @RequestMapping(value = "/{name}/page/{pageNo}", method = RequestMethod.GET)
    public String show(@PathVariable("name") String name, @PathVariable("pageNo") int pageNo, Model model) {
        Tag tag = tagManager.findTagByName(name);
        model.addAttribute("tag", tag);
        Page<PostTag> postTagPage = tagManager.findPostTagByTag(tag, pageNo);
        model.addAttribute("postTagPage", postTagPage);
        return "/tag/show";
    }
}
