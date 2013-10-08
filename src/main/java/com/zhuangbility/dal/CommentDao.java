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

import com.zhuangbility.model.Comment;
import com.zhuangbility.model.Post;

/**
 * 回复相关数据库访问类
 *
 * @author Howard.Li
 */
public interface CommentDao extends PagingAndSortingRepository<Comment, Long> {

    /**
     * 通过帖子查找回复的分页信息
     *
     * @param post 帖子信息
     * @param page 分页信息
     * @return     回复的分页信息
     */
    public Page<Comment> findByPost(Post post, Pageable page);

    /**
     * 通过回复id和帖子查找回复信息
     *
     * @param id   回复id
     * @param post 帖子
     * @return     回复信息
     */
    public Comment findByIdAndPost(Long id, Post post);

    /**
     * 通过帖子删除回复信息
     *
     * @param post 帖子信息
     */
    @Modifying
    @Query("delete Comment comment where comment.post=?1")
    public void deleteByPost(Post post);
}
