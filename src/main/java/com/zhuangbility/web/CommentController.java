/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import com.zhuangbility.web.form.CommentForm;

/**
 * 
 *
 * @author HowardLi
 */
@Controller
@RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.GET)
public class CommentController {

    @Autowired
    private PostManager    postManager;
    @Autowired
    private CommentManager commentManager;

    @RequestMapping(value = "/page/{pageNo}", method = RequestMethod.GET)
    public String show(@PathVariable("postId") Long postId, @PathVariable("pageNo") int pageNo, Model model) {
        Post post = postManager.findById(postId);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        model.addAttribute("post", post);
        Page<Comment> commentPage = commentManager.findByPost(post, pageNo);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("commentForm", new CommentForm());
        return "/post/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid CommentForm commentForm, BindingResult result, @PathVariable("postId") Long postId,
            Model model, HttpServletRequest request) {
        Post post = postManager.findById(postId);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "post/show";
        }
        if (commentForm.getContent().indexOf("://") != -1) {
            result.rejectValue("content", null, "不能含有URL");
            model.addAttribute("post", post);
            return "post/show";
        }
        Comment comment = new Comment();
        comment.setPost(post);
        if (SecurityUtils.getSubject().getPrincipal() == null) {
            comment.setUserName(commentForm.getUserName());
            comment.setUserEmail(commentForm.getUserEmail());
        } else {
            comment.setUserName("admin");
            comment.setUserEmail("zhuangbility.com@gmail.com");
            if (commentForm.getReplyCommentId() != null) {
                Comment replyComment = commentManager.findById(commentForm.getReplyCommentId());
                if (replyComment == null) {
                    throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
                }
                comment.setReplyUserName(replyComment.getUserName());
            }
        }
        String ip = request.getRemoteAddr();
        if (StringUtils.isNotBlank(request.getHeader("x-forwarded-for"))) {
            ip = request.getHeader("x-forwarded-for");
        }
        comment.setIp(ip);
        comment.setContent(commentForm.getContent());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        commentManager.save(comment);
        return "redirect:/post/" + postId;
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public void destroyComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        Post post = postManager.findById(postId);
        if (post == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        Comment comment = commentManager.findByIdAndPost(commentId, post);
        if (comment == null) {
            throw new ZhuangbilityException(ExceptionEnum.ERROR_404);
        }
        commentManager.delete(comment);
    }

}
