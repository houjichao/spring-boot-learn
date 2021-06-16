package com.hjc.learn.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author houjichao
 */
@Configuration
public class MyRequestInterceptor implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
