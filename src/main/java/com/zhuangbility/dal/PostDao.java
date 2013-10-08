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

/**
 * 帖子相关数据库访问类
 *
 * @author Howard.Li
 */
public interface PostDao extends PagingAndSortingRepository<Post, Long> {

    /**
     * 通过账户查找帖子的分页信息
     *
     * @param page 分页信息
     * @return     帖子的分页信息
     */
    public Page<Post> findAll(Pageable page);

    /**
     * 增加帖子中回复的计数
     *
     * @param post 帖子信息
     */
    @Modifying
    @Query("update Post post set post.commentCount=post.commentCount+1 where post=?1")
    void addCommentCountByPost(Post post);

    /**
     * 减少帖子中回复的计数
     *
     * @param post 帖子信息
     */
    @Modifying
    @Query("update Post post set post.commentCount=post.commentCount-1 where post=?1")
    void minusCommentCountByPost(Post post);
}
