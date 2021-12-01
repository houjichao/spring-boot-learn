package com.hjc.learn.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author houjichao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Module {
    /**
     * 模块类型
     * handle 处理器 AdapterConfig.MODULE_HANDLE
     * output 结果处理器 AdapterConfig.MODULE_OUTPUT
     *
     * @return
     */
    String type();
}