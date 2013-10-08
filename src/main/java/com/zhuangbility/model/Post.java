/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 帖子信息类
 *
 * @author Howard.Li
 */
@Entity
@Table(name = "z_post")
public class Post extends IdTimeModel {

    /**
     * 分页每页数量
     */
    public static final int SIZE         = 5;

    /**
     * 帖子标题
     */
    private String          title;

    /**
     * 帖子内容
     */
    private String          content;

    /**
     * 回复数量
     */
    private Integer         commentCount = 0;

    /**
     * 帖子关联的标签的关联关系
     */
    @OneToMany(mappedBy = "id.post")
    private List<PostTag>   postTags;

    /**
     * 帖子的回复
     */
    @OneToMany(mappedBy = "post")
    private List<Comment>   comments;

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
     * @return the postTags
     */
    public List<PostTag> getPostTags() {
        return postTags;
    }

    /**
     * @param postTags the postTags to set
     */
    public void setPostTags(List<PostTag> postTags) {
        this.postTags = postTags;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

}
