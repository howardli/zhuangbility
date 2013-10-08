/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

import com.zhuangbility.bll.ZhuangbilityException;
import com.zhuangbility.common.ExceptionEnum;

/**
 * 安全相关工具类
 *
 * @author Howard.Li
 */
public class SecurityUtil {

    /**
     * 加密使用的hash算法
     */
    public static final String  HASH_ALGORITHM   = "SHA-1";

    /**
     * 加密的hash次数
     */
    public static final int     HASH_INTERATIONS = 1024;

    /**
     * 生成盐的位数
     */
    private static final int    SALT_SIZE        = 8;

    /**
     * 生成盐的随机数
     */
    private static SecureRandom random           = new SecureRandom();

    /**
     * 获得加密的盐
     *
     * @return 盐
     */
    public static String getSalt() {
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }

    /**
     * 通过明文密码和盐获得密文
     *
     * @param plainText 明文密码
     * @param salt      盐
     * @return          密文密码
     */
    public static String encryption(String plainText, String salt) {
        byte[] hashPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            if (salt != null) {
                digest.update(Hex.decodeHex(salt.toCharArray()));
            }
            byte[] result = digest.digest(plainText.getBytes());
            for (int i = 1; i < HASH_INTERATIONS; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            hashPassword = result;
        } catch (Exception e) {
            throw new ZhuangbilityException(ExceptionEnum.UNKNOW_ERROR);
        }
        return Hex.encodeHexString(hashPassword);
    }

}
