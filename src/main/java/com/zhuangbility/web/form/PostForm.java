/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zhuangbility.web.annotation.Tag;

/**
 * 帖子的表单
 *
 * @author HowardLi
 */
public class PostForm {

    /**
     * 标题
     */
    @NotBlank
    @Length(max = 64)
    private String title;

    /**
     * 内容
     */
    @NotBlank
    private String content;

    /**
     * 标签
     */
    @NotBlank
    @Tag
    private String tag;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

}
