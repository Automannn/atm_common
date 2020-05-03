package com.automannn.common.web.interceptor;

import com.automannn.common.web.BaseControllerImpl;
import com.automannn.common.web.bean.ResultBean;
import com.automannn.common.web.configProperties.AtmSystemProperties;
import com.automannn.common.web.exception.AppException;
import com.automannn.common.web.util.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author automannn@163.com
 * @time 2020/5/3 21:46
 */

@ControllerAdvice
@Order(101)
public class BaseControllerExceptionInterceptor extends BaseControllerImpl {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerExceptionInterceptor.class);
    @Autowired
    private AtmSystemProperties atmSystemProperties;
    public BaseControllerExceptionInterceptor() {
    }

    @ExceptionHandler({AppException.class})
    @ResponseBody
    public ResultBean appExceptionHandler(AppException exception) {
        String errorMsgs = exception.getMessage();
        if (null == errorMsgs) {
            if (logger.isErrorEnabled()) {
                logger.error("AppException msg = {},return default message", errorMsgs);
            }

            return this.interServerError();
        } else {
            this.setErrors(exception.getErrorCode(), new String[]{errorMsgs});
            this.setSuccess(false);
            if (logger.isErrorEnabled()) {
                logger.error("AppException msg = {},exception = {}", exception.getMessage(), exception);
            }

            return this.result();
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultBean methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException validException) {
        this.recordSystemException(request, validException);
        BindingResult bindingResult = validException.getBindingResult();
        if (bindingResult.getAllErrors().isEmpty()) {
            if (logger.isErrorEnabled()) {
                logger.error("MethodArgumentNotValidException bindingResult = {},return default message", bindingResult);
            }

            return this.interServerError();
        } else {
            Iterator var4 = bindingResult.getAllErrors().iterator();

            while(var4.hasNext()) {
                ObjectError allError = (ObjectError)var4.next();
                FieldError error = (FieldError)allError;
                String field = error.getField();
                String defaultMessage = error.getDefaultMessage();
                this.setValidError("417", field, defaultMessage);
            }

            this.setSuccess(false);
            if (logger.isErrorEnabled()) {
                logger.error("MethodArgumentNotValidException msg = {}, exception = {}", validException.getMessage(), validException);
            }

            return this.result();
        }
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResultBean bindExceptionHandler(HttpServletRequest request, BindException bindException) {
        this.recordSystemException(request, bindException);
        BindingResult bindingResult = bindException.getBindingResult();
        if (bindingResult.getAllErrors().isEmpty()) {
            if (logger.isErrorEnabled()) {
                logger.error("BindException bindingResult = {},return default message", bindingResult);
            }

            return this.interServerError();
        } else {
            Iterator var4 = bindingResult.getAllErrors().iterator();

            while(var4.hasNext()) {
                ObjectError allError = (ObjectError)var4.next();
                FieldError error = (FieldError)allError;
                String field = error.getField();
                String defaultMessage = error.getDefaultMessage();
                this.setValidError("417", field, defaultMessage);
            }

            this.setSuccess(false);
            if (logger.isErrorEnabled()) {
                logger.error("BindException msg = {}, exception = {}", bindException.getMessage(), bindException);
            }

            return this.result();
        }
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultBean httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        this.recordSystemException(request, exception);
        String message = exception.getMessage();
        ResultBean result = (ResultBean) ServiceLocator.getAppContext().getBean(ResultBean.class);
        result.setSuccess(false);
        result.addError(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), message);
        if (logger.isErrorEnabled()) {
            logger.error("HttpRequestMethodNotSupportedException msg = {}, exception = {}", message, exception);
        }

        return result;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResultBean constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException constraintViolationException) {
        this.recordSystemException(request, constraintViolationException);
        String detailMsg = constraintViolationException.getMessage();
        if (null == detailMsg) {
            if (logger.isErrorEnabled()) {
                logger.error("ConstraintViolationException msg = {},return default message", detailMsg);
            }

            return this.interServerError();
        } else {
            String substr = detailMsg.substring(0, detailMsg.indexOf(46));
            this.setError("417", detailMsg.replaceAll(substr + ".", ""));
            this.setSuccess(false);
            if (logger.isErrorEnabled()) {
                logger.error("ConstraintViolationException msg = {},exception = {}", detailMsg, constraintViolationException);
            }

            return this.result();
        }
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultBean exceptionHandler(HttpServletRequest request, Exception exception) {
        String exceptionId = UUID.randomUUID().toString().replace("-", "");
//        this.recordSystemException(exceptionId, request, exception);
        if (logger.isErrorEnabled()) {
            logger.error("Exception msg = {}, exception = {}", exception.getMessage(), exception);
        }

        return this.interServerErrorWithExceptionId(exceptionId);
    }

    private ResultBean interServerErrorWithExceptionId(String exceptionId) {
        this.setError("500", this.atmSystemProperties.getInterErrorMsg() + ",错误ID:" + exceptionId);
        this.setSuccess(false);
        return this.result();
    }

    private ResultBean interServerError() {
        this.setError("500", this.atmSystemProperties.getInterErrorMsg());
        this.setSuccess(false);
        return this.result();
    }

    private void recordSystemException(HttpServletRequest request, Exception exception) {
//        this.recordSystemException(UUIDUtils.getUUID(), request, exception);
        //todo: 记录异常
    }


}
