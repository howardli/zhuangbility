/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 回复的表单
 *
 * @author HowardLi
 */
public class CommentForm {

    /**
     * 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String userName;

    /**
     * 邮箱
     */
    @NotBlank
    @Length(max = 64)
    @Email
    private String userEmail;

    /**
     * 内容
     */
    @NotBlank
    @Length(max = 512)
    private String content;

    private Long   replyCommentId;

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
     * @return the replyCommentId
     */
    public Long getReplyCommentId() {
        return replyCommentId;
    }

    /**
     * @param replyCommentId the replyCommentId to set
     */
    public void setReplyCommentId(Long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

}
