/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.model.convertor;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 标签相关的转换类
 *
 * @author Howard.Li
 */
public class TagConvertor {

    /**
     * 把字符串的标签名字转换成标签名字的列表
     *
     * @param tagString 标签名字字符串
     * @return          标签名字列表
     */
    public static List<String> string2List(String tagString) {
        List<String> tagNameList = new LinkedList<String>();
        if (StringUtils.isNotBlank(tagString)) {
            String[] tagArray = StringUtils.split(tagString, " |,|，");
            for (String tagName : tagArray) {
                if (StringUtils.isNotBlank(tagName)) {
                    tagNameList.add(tagName);
                }
            }
        }
        return tagNameList;
    }
}
