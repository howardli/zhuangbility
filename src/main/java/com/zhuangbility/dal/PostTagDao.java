/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zhuangbility.model.Post;
import com.zhuangbility.model.PostTag;
import com.zhuangbility.model.Tag;
import com.zhuangbility.model.pk.PostTagPk;

/**
 * 帖子和标签关联关系相关数据库访问类
 *
 * @author Howard.Li
 */
public interface PostTagDao extends PagingAndSortingRepository<PostTag, PostTagPk> {

    /**
     * 通过帖子删除帖子和标签关联关系
     *
     * @param post 帖子信息
     */
    @Modifying
    @Query("delete PostTag postTag where postTag.id.post=?1")
    void deleteByPost(Post post);

    /**
     * 通过标签删除帖子和标签关联关系
     *
     * @param tag 标签信息
     */
    @Modifying
    @Query("delete PostTag postTag where postTag.id.tag=?1")
    void deleteByTag(Tag tag);

    /**
     * 通过标签查找帖子和标签关联关系的分页信息
     *
     * @param tag      标签信息
     * @param pageable 分页信息
     * @return         帖子和标签关联关系的分页信息
     */
    Page<PostTag> findByIdTag(Tag tag, Pageable pageable);

}
