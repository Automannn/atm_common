package com.automannn.common.web.exception;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author automannn@163.com
 * @time 2020/5/3 21:48
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = -8909342639087473585L;
    private static final String ERROR_CODE;
    private String errorMessage;

    public AppException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public AppException(Exception exception) {
        super(exception);
        this.errorMessage = exception.getMessage();
    }

    public String getErrorCode() {
        return ERROR_CODE;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    static {

        String host = null;
        try {
            host =String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
        }
        StringBuilder code = new StringBuilder();
        if (null != host) {
            code.append(host.replace(".", "-")).append("-").reverse();
        }else {
            code.append("unknown-host");
        }

        ERROR_CODE = code.toString();
    }
}
