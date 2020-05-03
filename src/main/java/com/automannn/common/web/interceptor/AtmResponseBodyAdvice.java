package com.automannn.common.web.interceptor;

import com.automannn.common.web.bean.ResultBean;
import com.automannn.common.web.interceptor.advice.AtmAdviceResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author automannn@163.com
 * @time 2020/5/3 18:58
 */
@Order(20)
@ControllerAdvice
public class AtmResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private AtmAdviceResolver atmAdviceResolver;

    public AtmResponseBodyAdvice(AtmAdviceResolver atmAdviceResolver) {
        this.atmAdviceResolver = atmAdviceResolver;
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (null == returnType.getMethod()) {
            return false;
        } else {
            if (returnType.getMethodAnnotations().length > 0) {
                Annotation[] var3 = returnType.getMethodAnnotations();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Annotation annotation = var3[var5];
                    if (annotation instanceof ResponseBody) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResultBean resultBean = this.resolver(body, methodParameter, request);
        return this.atmAdviceResolver.resolveResponseBody(resultBean, methodParameter, selectedContentType, selectedConverterType, request, response);

    }

    private ResultBean resolver(Object body, MethodParameter methodParameter, ServerHttpRequest request) {
        ResultBean resultBean = (ResultBean)((ServletServerHttpRequest)request).getServletRequest().getAttribute("_WEB_RESULT_BEAN");
        if (methodParameter.getMethod() == null) {
            return resultBean;
        } else {
            String returnTypeName = methodParameter.getMethod().getReturnType().getName();
            if ("void".equals(returnTypeName)) {
                return resultBean;
            } else {
                resultBean.addField("resultData", body);
                return resultBean;
            }
        }
    }
}
