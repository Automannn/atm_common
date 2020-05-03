package com.automannn.common.web.bean;

import lombok.Data;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:24
 */
@Data
public class Error {

    private String errorCode;
    private String parameter;
    private String msg;

    public Error() {
    }

    public Error(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public Error(String errorCode, String parameter, String msg) {
        this.errorCode = errorCode;
        this.parameter = parameter;
        this.msg = msg;
    }
}
