package com.example.config;

import com.example.domain.extend.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration =  registry.addInterceptor(tokenInterceptor);
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns("/resources/**","/static/**","/user/login","/user/insert","/user/logout","/article/publicList","/article/view","/article/addViews","/article/listRecommend");
    }
}
