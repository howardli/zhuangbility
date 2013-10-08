/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhuangbility.dal.AccountDao;
import com.zhuangbility.model.Account;

/**
 * 账户相关操作类
 * 
 * @author Howard.Li
 */
@Component
@Transactional(readOnly = true)
public class AccountManager {

    /**
     * 账户相关数据库访问类
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * 通过邮箱查询账户信息
     *
     * @param email 邮箱
     * @return      账户信息
     */
    public Account findAccountByEmail(String email) {
        return accountDao.findByEmail(email);
    }

}
