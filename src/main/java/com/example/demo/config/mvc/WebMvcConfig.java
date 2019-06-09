package com.example.demo.config.mvc;

import com.example.demo.config.mvc.interceptor.RequestTimeConsumingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截器
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeConsumingInterceptor())
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/v2/api-docs");
    }

    /**
     * 添加可访问的静态资源
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
