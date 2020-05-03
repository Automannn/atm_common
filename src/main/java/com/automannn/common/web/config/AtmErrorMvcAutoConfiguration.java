package com.automannn.common.web.config;

import com.automannn.common.web.configProperties.AtmSystemProperties;
import com.automannn.common.web.exception.AtmDefaultErrorResolver;
import com.automannn.common.web.exception.AtmErrorBaseController;
import com.automannn.common.web.exception.AtmErrorResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author automannn@163.com
 * @time 2020/5/3 21:29
 */
@Configuration
@ConditionalOnProperty(
        name = {"atm.internalErrorResolver", "atm.internal-error-resolver"},
        havingValue = "true",
        matchIfMissing = true
)
public class AtmErrorMvcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(
            value = {AtmErrorResolver.class},
            search = SearchStrategy.CURRENT
    )
    public AtmErrorResolver defaultErrorBaseController(AtmSystemProperties systemProperties, ErrorAttributes errorAttributes) {
        return new AtmDefaultErrorResolver(systemProperties, errorAttributes);
    }

    @Bean
    public AtmErrorBaseController atmErrorBaseController(ServerProperties serverProperties, AtmErrorResolver errorResolver) {
        return new AtmErrorBaseController(errorResolver, serverProperties);
    }
}
