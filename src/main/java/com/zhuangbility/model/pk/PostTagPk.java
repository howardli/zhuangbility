/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.zhuangbility.model.Post;
import com.zhuangbility.model.Tag;

/**
 * 帖子和标签的关联关系联合主键类
 *
 * @author Howard.Li
 */
@SuppressWarnings("serial")
@Embeddable
public class PostTagPk implements Serializable {

    /**
     * 帖子信息
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 标签信息
     */
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag  tag;

    /**
     * 构造方法
     */
    public PostTagPk() {
    }

    /**
     * 构造方法
     *
     * @param post 帖子信息
     * @param tag  标签信息
     */
    public PostTagPk(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }

    /**
     * 重载hashCode，只比较帖子和标签
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "post", "tag");
    }

    /**
     * 重载equals，只比较帖子和标签
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "post", "tag");
    }

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
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
