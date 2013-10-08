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

import com.zhuangbility.dal.PostTagDao;
import com.zhuangbility.dal.TagDao;
import com.zhuangbility.model.Post;
import com.zhuangbility.model.PostTag;
import com.zhuangbility.model.Tag;

/**
 * 
 *
 * @author HowardLi
 */
@Component
@Transactional(readOnly = true)
public class TagManager {

    @Autowired
    private TagDao     tagDao;
    @Autowired
    private PostTagDao postTagDao;

    public Page<PostTag> findPostTagByTag(Tag tag, int pageNo) {
        PageRequest pageRequest = new PageRequest(pageNo - 1, Post.SIZE, new Sort(Direction.DESC, "id.post.id"));
        return postTagDao.findByIdTag(tag, pageRequest);
    }

    public Tag findTagByName(String name) {
        return tagDao.findByName(name);
    }

    public Iterable<Tag> findAll() {
        return tagDao.findAll(new Sort(Direction.DESC, "postCount"));
    }
}
