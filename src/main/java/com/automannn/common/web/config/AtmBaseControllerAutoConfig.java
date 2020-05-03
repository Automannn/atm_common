package com.automannn.common.web.config;

import com.automannn.common.web.interceptor.advice.AtmAdviceResolver;
import com.automannn.common.web.interceptor.advice.AtmDefaultAdviceResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author automannn@163.com
 * @time 2020/5/3 18:38
 */
@Configuration
@ComponentScan("com.automannn.common.web")
public class AtmBaseControllerAutoConfig {

    @Bean
    @ConditionalOnMissingBean(
            value = {AtmAdviceResolver.class},
            search = SearchStrategy.CURRENT
    )
    public AtmAdviceResolver adviceResolver() {
        return new AtmDefaultAdviceResolver();
    }
}
