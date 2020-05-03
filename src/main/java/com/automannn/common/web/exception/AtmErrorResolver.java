package com.automannn.common.web.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author automannn@163.com
 * @time 2020/5/3 20:47
 */
public interface AtmErrorResolver {
    void resolveException(HttpServletRequest request, ErrorProperties errorProperties);

}
