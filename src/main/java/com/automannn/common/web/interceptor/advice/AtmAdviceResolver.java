package com.automannn.common.web.interceptor.advice;

import com.automannn.common.web.bean.ResultBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.awt.*;

/**
 * @author automannn@163.com
 * @time 2020/5/3 18:43
 */
public interface AtmAdviceResolver {
    Object resolveResponseBody(ResultBean resultBean, MethodParameter returnType,
                               MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                               ServerHttpRequest request, ServerHttpResponse response);
}
