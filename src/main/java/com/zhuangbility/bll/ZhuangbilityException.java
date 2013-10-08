/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.bll;

import com.zhuangbility.common.ExceptionEnum;

/**
 * 业务的运行时异常
 *
 * @author Howard.Li
 */
@SuppressWarnings("serial")
public class ZhuangbilityException extends RuntimeException {

    /**
     * 异常信息枚举
     */
    private ExceptionEnum exceptionEnum;

    /**
     * 构造方法
     */
    public ZhuangbilityException() {
        super();
    }

    /**
     * 构造方法
     *
     * @param exceptionEnum 异常信息枚举
     */
    public ZhuangbilityException(ExceptionEnum exceptionEnum) {
        super();
        this.exceptionEnum = exceptionEnum;
    }

    /**
     * @return the exceptionEnum
     */
    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

}
