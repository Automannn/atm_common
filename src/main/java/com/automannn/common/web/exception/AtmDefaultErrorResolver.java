package com.automannn.common.web.exception;

import com.automannn.common.web.BaseControllerImpl;
import com.automannn.common.web.configProperties.AtmSystemProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author automannn@163.com
 * @time 2020/5/3 20:56
 */
public class AtmDefaultErrorResolver extends BaseControllerImpl implements AtmErrorResolver {
    private AtmSystemProperties atmSystemProperties;
    private ErrorAttributes errorAttributes;

    public AtmDefaultErrorResolver(AtmSystemProperties atmSystemProperties, ErrorAttributes errorAttributes) {
        this.atmSystemProperties = atmSystemProperties;
        this.errorAttributes = errorAttributes;
    }

    @Override
    public void resolveException(HttpServletRequest request, ErrorProperties errorProperties) {
        HttpStatus status = this.getStatus(request);
        this.setSuccess(false);
        if (HttpStatus.NOT_FOUND.equals(status)) {
            this.setError("404", "服务不存在，访问失败！");
        } else {
            this.setError("500", this.atmSystemProperties.getInterErrorMsg());
        }
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }

    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, ErrorProperties errorProperties) {
        ErrorProperties.IncludeStacktrace include = errorProperties.getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else {
            return include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM ? this.getTraceParameter(request) : false;
        }
    }

    protected boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        } else {
            return !"false".equalsIgnoreCase(parameter);
        }
    }
}
