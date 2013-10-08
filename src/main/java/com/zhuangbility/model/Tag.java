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
 * 标签信息类
 *
 * @author Howard.Li
 */
@Entity
@Table(name = "z_tag")
public class Tag extends IdTimeModel {

    /**
     * 标签名称
     */
    private String        name;

    /**
     * 帖子数量
     */
    private Integer       postCount = 0;

    /**
     * 标签关联的帖子关联关系
     */
    @OneToMany(mappedBy = "id.tag")
    private List<PostTag> postTags;

    /**
     * 构造方法
     */
    public Tag() {
    }

    /**
     * 构造方法
     *
     * @param name 标签名称
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the postCount
     */
    public Integer getPostCount() {
        return postCount;
    }

    /**
     * @param postCount the postCount to set
     */
    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

}
