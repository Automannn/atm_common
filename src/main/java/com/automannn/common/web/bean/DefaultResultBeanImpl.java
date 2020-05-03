package com.automannn.common.web.bean;

import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:21
 */
public class DefaultResultBeanImpl implements ResultBean {
    private int code = 200;
    private String requestId;
    private Data data = new Data();
    private List<Error> errors = new ArrayList();
    private boolean serviceSuccess;
    private String redirectUrl;

    public DefaultResultBeanImpl() {
        this.serviceSuccess = Boolean.TRUE;
    }

    public void addField(String field, Object value) {
        if (null != field) {
            this.data.put(field, value);
        }

    }

    public void addMap(Map map) {
        if (!map.isEmpty()) {
            this.data.putAll(map);
        }

    }

    public void addError(@NonNull String errorCode, String msg) {
        this.errors.add(new Error(errorCode, msg));
    }

    public void addError(@NonNull String errorCode, String paramter, String msg) {
        this.errors.add(new Error(errorCode, paramter, msg));
    }

    public void setSuccess(boolean success) {
        this.setServiceSuccess(success);
    }

    public void setData(@NonNull Map<String, Object> data, boolean clear) {
        if (clear) {
            this.getData().clear();
        }

        this.getData().putAll(data);
    }

    public void setCode(int httpCode) {
        this.code = httpCode;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setServiceSuccess(boolean serviceSuccess) {
        this.serviceSuccess = serviceSuccess;
    }

    public int getCode(HttpServletResponse response) {
        return response == null ? this.code : response.getStatus();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Data getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }

    public boolean isServiceSuccess() {
        return this.serviceSuccess;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
