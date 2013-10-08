/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.zhuangbility.model.pk.PostTagPk;

/**
 * 帖子和标签的关联关系类
 *
 * @author Howard.Li
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "z_post_tag")
public class PostTag implements Serializable {

    /**
     * 帖子和标签的关联关系联合主键id
     */
    @EmbeddedId
    private PostTagPk id = new PostTagPk();

    /**
     * 创建时间
     */
    private Date      createTime;

    /**
     * 更新时间
     */
    private Date      updateTime;

    /**
     * 构造方法
     */
    public PostTag() {
    }

    /**
     * 构造方法
     */
    public PostTag(Post post, Tag tag) {
        id = new PostTagPk(post, tag);
    }

    /**
     * 重载hashCode，只比较帖子和标签
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }

    /**
     * 重载equals，只比较帖子和标签
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "id");
    }

    /**
     * @return the id
     */
    public PostTagPk getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(PostTagPk id) {
        this.id = id;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
