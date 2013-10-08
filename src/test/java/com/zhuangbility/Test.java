/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.oro.text.perl.Perl5Util;

import com.zhuangbility.bll.security.SecurityUtil;

/**
 * 测试类（不确定的一些方法使用可以在里面先测试）
 *
 * @author Howard.Li
 */
public class Test {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String password = "howard23";
        String salt = SecurityUtil.getSalt();
        System.out.println("salt=" + salt + ",password=" + SecurityUtil.encryption(password, salt));
        Perl5Util perl = new Perl5Util();
        System.out.println(perl.match("/^(?!\\$screen_content).*$/", "$screen_content"));
        System.out.println(perl.match("/^(?!\\$screen_content).*$/", "$post"));
        System.out.println(perl.match("/^\\$screen_content$/", "$screen_content"));
        Pattern pattern = Pattern.compile("[<>]>*]>?");
        Matcher matcher = pattern.matcher("<div><div>\r\n>>dsfsdf<a>dddd</a>");
        while (matcher.find()) {
            System.out.println(matcher.group() + "------");
        }
        System.out.println("dddd http://sdsd".indexOf("://") != -1);
    }
}
