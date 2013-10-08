/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhuangbility.bll.ZhuangbilityException;
import com.zhuangbility.bll.manager.CommentManager;
import com.zhuangbility.bll.manager.PostManager;
import com.zhuangbility.common.ExceptionEnum;
import com.zhuangbility.model.Comment;
import com.zhuangbility.model.Post;
import com.zhuangbility.model.PostTag;
import com.zhuangbility.model.convertor.TagConvertor;
import com.zhuangbility.web.annotation.Login;
import com.zhuangbility.web.form.CommentForm;
import com.zhuangbility.web.form.PostForm;

/**
 * 帖子相关控制器类
 *
 * @author Howard.Li
 */
@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostManager    postManager;
    @Autowired
    private CommentManager commentManager;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @Login
    public String _new(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "/post/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Login
    public String create(@Valid PostForm postForm, BindingResult result) {
        if (result.hasErrors()) {
            return "post/new";
        }
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        List<String> tagNamelist = TagConvertor.string2List(postForm.getTag());
        post = postManager.save(post, tagNamelist);
        return "redirect:/post/" + post.getId();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @Login
    public String edit(@PathVariable("id") Long id, Model model) {
        Post post = postManager.findById(id);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        PostForm postForm = new PostForm();
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());
        List<PostTag> postTags = post.getPostTags();
        List<String> tagNames = new LinkedList<String>();
        for (PostTag postTag : postTags) {
            tagNames.add(postTag.getId().getTag().getName());
        }
        postForm.setTag(StringUtils.join(tagNames, " "));
        model.addAttribute("postForm", postForm);
        model.addAttribute("postId", id);
        return "post/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Login
    public String update(@PathVariable("id") Long id, @Valid PostForm postForm, BindingResult result, Model model) {
        model.addAttribute("postId", id);
        Post post = postManager.findById(id);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        if (result.hasErrors()) {
            return "post/edit";
        }
        List<String> tagNamelist = TagConvertor.string2List(postForm.getTag());
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setUpdateTime(new Date());
        post = postManager.save(post, tagNamelist);
        return "redirect:/post/" + post.getId();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Login
    public void destroy(@PathVariable("id") Long id) {
        Post post = postManager.findById(id);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        postManager.delete(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        Post post = postManager.findById(id);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        model.addAttribute("post", post);
        Page<Comment> commentPage = commentManager.findByPost(post, 1);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("commentForm", new CommentForm());
        return "/post/show";
    }
}
