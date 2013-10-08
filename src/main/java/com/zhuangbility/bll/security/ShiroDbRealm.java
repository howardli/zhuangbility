/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll.security;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhuangbility.bll.ZhuangbilityException;
import com.zhuangbility.bll.manager.AccountManager;
import com.zhuangbility.common.ExceptionEnum;
import com.zhuangbility.model.Account;

/**
 * 认证和授权相关操作类
 *
 * @author Howard.Li
 */
public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 账户相关操作类
     */
    private AccountManager accountManager;

    /**
     * 认证时校验是否登陆合法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Account account = accountManager.findAccountByEmail(token.getUsername());
        if (account != null) {
            byte[] salt;
            try {
                salt = Hex.decodeHex(account.getSalt().toCharArray());
            } catch (DecoderException e) {
                throw new ZhuangbilityException(ExceptionEnum.UNKNOW_ERROR);
            }
            return new SimpleAuthenticationInfo(account, account.getPassword(), ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SecurityUtil.HASH_ALGORITHM);
        matcher.setHashIterations(SecurityUtil.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 给当前用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("admin");
        return info;
    }

    /**
     * @param accountManager the accountManager to set
     */
    @Autowired
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

}
