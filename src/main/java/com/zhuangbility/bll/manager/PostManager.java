/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll.manager;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhuangbility.dal.CommentDao;
import com.zhuangbility.dal.PostDao;
import com.zhuangbility.dal.PostTagDao;
import com.zhuangbility.dal.TagDao;
import com.zhuangbility.model.Post;
import com.zhuangbility.model.PostTag;
import com.zhuangbility.model.Tag;

/**
 * 帖子相关操作类
 *
 * @author Howard.Li
 */
@Component
@Transactional(readOnly = true)
public class PostManager {

    /**
     * 帖子相关数据库访问类
     */
    @Autowired
    private PostDao    postDao;

    /**
     * 标签相关数据库访问类
     */
    @Autowired
    private TagDao     tagDao;

    /**
     * 回复相关数据库访问类
     */
    @Autowired
    private CommentDao commentDao;

    /**
     * 帖子和标签关联关系相关数据库访问类
     */
    @Autowired
    private PostTagDao postTagDao;

    /**
     * 通过账户查找帖子的分页信息
     *
     * @param pageNo 帖子的页数
     * @return       帖子的分页信息
     */
    public Page<Post> findAll(int pageNo) {
        PageRequest pageRequest = new PageRequest(pageNo - 1, Post.SIZE, new Sort(Direction.DESC, "id"));
        return postDao.findAll(pageRequest);
    }

    /**
     * 保存帖子信息
     *
     * @param post        帖子信息
     * @param newTagNames 新的帖子标签名字列表
     * @return            帖子信息
     */
    @Transactional(readOnly = false)
    public Post save(Post post, List<String> newTagNames) {
        post = postDao.save(post);
        List<PostTag> oldPostTags = post.getPostTags();
        if (oldPostTags == null) {
            oldPostTags = new LinkedList<PostTag>();
        }
        Date date = new Date();
        List<Tag> newTags = new LinkedList<Tag>();
        for (String newTagName : newTagNames) {
            Tag tag = tagDao.findByName(newTagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(newTagName);
                tag.setCreateTime(date);
                tag.setUpdateTime(date);
                tagDao.save(tag);
            }
            newTags.add(tag);
        }
        savePostTags(post, newTags, oldPostTags);
        tagDao.deleteUnless();
        return post;
    }

    private void savePostTags(Post post, List<Tag> newTags, List<PostTag> oldPostTags) {
        for (PostTag oldPostTag : oldPostTags) {
            boolean isRepeat = false;
            for (Tag newTag : newTags) {
                if (oldPostTag.getId().getTag().getId() == newTag.getId()) {
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                postTagDao.delete(oldPostTag);
                tagDao.minusPostCountByTag(oldPostTag.getId().getTag());
            }
        }
        Date date = new Date();
        for (Tag newTag : newTags) {
            boolean isRepeat = false;
            for (PostTag oldPostTag : oldPostTags) {
                if (oldPostTag.getId().getTag().getId() == newTag.getId()) {
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                PostTag newPostTag = new PostTag(post, newTag);
                newPostTag.setCreateTime(date);
                newPostTag.setUpdateTime(date);
                postTagDao.save(newPostTag);
                tagDao.addPostCountByTag(newTag);
            }
        }
    }

    /**
     * 通过帖子id查询帖子信息
     *
     * @param id 帖子id
     * @return   帖子信息
     */
    public Post findById(Long id) {
        return postDao.findOne(id);
    }

    /**
     * 删除帖子信息
     *
     * @param post 帖子信息
     */
    @Transactional(readOnly = false)
    public void delete(Post post) {
        List<PostTag> postTags = post.getPostTags();
        for (PostTag postTag : postTags) {
            Tag tag = postTag.getId().getTag();
            tagDao.minusPostCountByTag(tag);
        }
        commentDao.deleteByPost(post);
        postTagDao.deleteByPost(post);
        postDao.delete(post);
        tagDao.deleteUnless();
    }
}
