/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.web.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.zhuangbility.model.convertor.TagConvertor;
import com.zhuangbility.web.annotation.Tag;

/**
 * 
 * @author Howard.Li
 */
public class TagValidator implements ConstraintValidator<Tag, String> {

    @Override
    public void initialize(Tag constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> tagNamelist = TagConvertor.string2List(value);
        if (tagNamelist.size() > 5) {
            return false;
        }
        for (String tagName : tagNamelist) {
            if (StringUtils.length(tagName) > 64) {
                return false;
            }
        }
        return true;
    }

}
