/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 回复信息类
 *
 * @author Howard.Li
 */
@Entity
@Table(name = "z_comment")
public class Comment extends IdTimeModel {

    /**
     * 分页每页数量
     */
    public static final int SIZE = 5;

    /**
     * 帖子信息
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post            post;

    /**
     * 回复者名字
     */
    private String          userName;

    /**
     * 回复者邮箱
     */
    private String          userEmail;

    /**
     * 回复内容
     */
    private String          content;

    /**
     * 被回复者名字
     */
    private String          replyUserName;

    /**
     * 回复者ip地址
     */
    private String          ip;

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the replyUserName
     */
    public String getReplyUserName() {
        return replyUserName;
    }

    /**
     * @param replyUserName the replyUserName to set
     */
    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}
