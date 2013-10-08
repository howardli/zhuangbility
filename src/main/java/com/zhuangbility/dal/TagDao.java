/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.dal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zhuangbility.model.Tag;

/**
 * 标签相关数据库访问类
 *
 * @author Howard.Li
 */
public interface TagDao extends PagingAndSortingRepository<Tag, Long> {

    /**
     * 通过标签名称查询标签信息
     *
     * @param name 标签名称
     * @return     标签信息
     */
    public Tag findByName(String name);

    /**
     * 增加标签中帖子的计数
     *
     * @param tag
     */
    @Modifying
    @Query("update Tag tag set tag.postCount=tag.postCount+1 where tag=?1")
    void addPostCountByTag(Tag tag);

    /**
     * 减少标签中帖子的计数
     *
     * @param tag
     */
    @Modifying
    @Query("update Tag tag set tag.postCount=tag.postCount-1 where tag=?1")
    void minusPostCountByTag(Tag tag);

    /**
     * 删除多余没有被使用的标签
     */
    @Modifying
    @Query("delete Tag tag where tag.postCount<=0")
    void deleteUnless();
}
