/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.dal;

import org.springframework.data.repository.CrudRepository;

import com.zhuangbility.model.Account;

/**
 * 账户相关数据库访问类
 * 
 * @author Howard.Li
 */
public interface AccountDao extends CrudRepository<Account, Long> {

    /**
     * 通过邮箱查询账户信息
     *
     * @param email 邮箱
     * @return      账户信息
     */
    public Account findByEmail(String email);

}
