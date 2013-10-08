/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhuangbility.dal.CommentDao;
import com.zhuangbility.dal.PostDao;
import com.zhuangbility.model.Comment;
import com.zhuangbility.model.Post;

/**
 * 回复相关操作类
 *
 * @author Howard.Li
 */
@Component
@Transactional(readOnly = true)
public class CommentManager {

    /**
     * 回复相关数据库访问类
     */
    @Autowired
    private CommentDao commentDao;

    /**
     * 帖子相关数据库访问类
     */
    @Autowired
    private PostDao    postDao;

    /**
     * 保存回复信息
     *
     * @param comment 回复信息
     * @return        回复信息
     */
    @Transactional(readOnly = false)
    public Comment save(Comment comment) {
        comment = commentDao.save(comment);
        Post post = comment.getPost();
        postDao.addCommentCountByPost(post);
        return comment;
    }

    /**
     * 通过回复id查询回复信息
     *
     * @param id 回复id
     * @return   回复信息
     */
    public Comment findById(Long id) {
        return commentDao.findOne(id);
    }

    /**
     * 删除回复信息
     *
     * @param comment 回复信息
     */
    @Transactional(readOnly = false)
    public void delete(Comment comment) {
        postDao.minusCommentCountByPost(comment.getPost());
        commentDao.delete(comment);
    }

    /**
     * 通过帖子查找回复的分页信息
     *
     * @param post   帖子信息
     * @param pageNo 回复的页数
     * @return       回复的分页信息
     */
    public Page<Comment> findByPost(Post post, int pageNo) {
        PageRequest pageRequest = new PageRequest(pageNo - 1, Comment.SIZE, new Sort(Direction.ASC, "id"));
        return commentDao.findByPost(post, pageRequest);
    }

    /**
     * 通过回复id和帖子查找回复信息
     *
     * @param id   回复id
     * @param post 帖子
     * @return     回复信息
     */
    public Comment findByIdAndPost(Long id, Post post) {
        return commentDao.findByIdAndPost(id, post);
    }

}
