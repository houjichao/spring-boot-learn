package com.hjc.learn.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author houjichao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Adapter {

    /**
     * 业务类型适配
     *
     * @return
     */
    Class<? extends BusinessAdapter> type() default BusinessAdapter.class;

    /**
     * 业务名称适配
     *
     * @return
     */
    String name() default "";

    /**
     * 业务类型
     *
     * @return
     */
    String businessType() default "";

}