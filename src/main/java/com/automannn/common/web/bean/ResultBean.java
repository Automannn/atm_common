package com.automannn.common.web.bean;

import java.util.Map;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:18
 */
public interface ResultBean {

    String WEB_RESULT_BEAN = "_WEB_RESULT_BEAN";

    void setCode(int code);

    void addField(String field, Object value);

    void addMap(Map map);

    void addError(String errorCode, String msg);

    void addError(String errorCode, String paramter, String msg);

    void setData(Map<String, Object> data, boolean clear);

    void setSuccess(boolean success);

    void setRequestId(String requestId);

    Data getData();

    default void setRedirectUrl(String redirectUrl) {
    }
}
