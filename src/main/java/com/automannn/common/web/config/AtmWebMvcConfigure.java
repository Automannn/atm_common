package com.automannn.common.web.config;

import com.automannn.common.web.configProperties.FastJsonProperties;
import com.automannn.common.web.interceptor.ResultBeanInterceptor;
import com.automannn.common.web.util.FastJsonConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author automannn@163.com
 * @time 2020/5/3 21:19
 */
@Configuration
public class AtmWebMvcConfigure implements WebMvcConfigurer {

    @Autowired
    private FastJsonProperties fastJsonProperties;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(FastJsonConverterFactory.getFastJsonConverter(fastJsonProperties));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResultBeanInterceptor()).addPathPatterns("/**");
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("/**"));
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/template/**"}).addResourceLocations(new String[]{"classpath:/template/"}).setCacheControl(CacheControl.maxAge(0L, TimeUnit.SECONDS).cachePublic());
    }
}
