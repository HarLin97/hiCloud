package com.fuchangling.config;

import com.fuchangling.interceptor.OperationInterceptor;
import com.fuchangling.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author wangzhen
 * @date 2020-01-04
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebMvcConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    private final OperationInterceptor operationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                //拦截所有请求
                .addPathPatterns("/**")
                //排除登录接口
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/auth/login/**")
                //排除swagger的请求
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**");

        // 操作日志
        registry.addInterceptor(operationInterceptor)
                .addPathPatterns("/**")
                //排除登录接口
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/csrf")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                .excludePathPatterns("/system/log")
                .excludePathPatterns("/auth/login/**")
                //排除swagger的请求
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**");


    }
}
