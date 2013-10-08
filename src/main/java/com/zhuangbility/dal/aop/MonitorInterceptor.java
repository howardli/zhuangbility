/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility.dal.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 性能监控日志拦截器
 *
 * @author Howard.Li
 */
@Component
public class MonitorInterceptor implements MethodInterceptor {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger("com.topiter.bll");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String className = invocation.getStaticPart().getClass().getSimpleName();
        String method = className + "." + invocation.getMethod().getName();
        boolean hasError = false;
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            //result赋值
            result = invocation.proceed();
            return result;
        } catch (Throwable ex) {
            hasError = true;
            throw ex;
        } finally {
            if (logger.isInfoEnabled()) {
                long elapseTime = System.currentTimeMillis() - startTime;
                StringBuffer sb = new StringBuffer();
                sb.append("[(").append(method);
                if (hasError) {
                    sb.append(",N,");
                } else {
                    sb.append(",Y,");
                }
                sb.append(elapseTime).append("ms)]").append(" - (args=");
                sb.append(ToStringBuilder.reflectionToString(invocation.getArguments(),
                        ToStringStyle.SHORT_PREFIX_STYLE));
                String resultString = null;
                if (result instanceof String) {
                    resultString = (String) result;
                } else {
                    resultString = ToStringBuilder.reflectionToString(result, ToStringStyle.SHORT_PREFIX_STYLE);
                }
                sb.append(") - (result=").append(resultString).append(")");
                logger.info(sb.toString());
            }
        }
    }
}
