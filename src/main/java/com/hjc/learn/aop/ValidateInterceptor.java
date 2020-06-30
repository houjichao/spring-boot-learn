package com.hjc.learn.aop;

import com.hjc.learn.message.MessageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 参数校验拦截器，拦截前端请求，对自定义参数按照JSR-303规范进行校验
 * @author houjichao
 */
@Aspect
@Component
public class ValidateInterceptor {
    private static final String POINT_CUT = "@annotation(org.springframework.web.bind.annotation.PostMapping)";

    @Autowired
    private MessageHelper messageHelper;

    /**
     * 定义切点，拦截POST请求，对请求中的自定义参数镜像校验
     */
    @Pointcut(POINT_CUT)
    public void pointcut() {
    }

    /**
     * 参数校验拦截器
     * @param joinPoint 用于获取请求参数信息
     */
    @Before("pointcut()")
    public void valid(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                continue;
            }
            // 参数校验
            messageHelper.valid(arg);
        }
    }
}
