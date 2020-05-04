package com.automannn.common.web.config;

import com.automannn.common.web.bean.DefaultResultBeanImpl;
import com.automannn.common.web.bean.ResultBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author automannn@163.com
 * @time 2020/5/4 9:21
 */
@Configuration
public class AtmWebReturnBeanConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public ResultBean resultBean() {
        return new DefaultResultBeanImpl();
    }
}
