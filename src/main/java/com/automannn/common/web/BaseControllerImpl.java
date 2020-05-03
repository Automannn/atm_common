package com.automannn.common.web;

import com.automannn.common.web.bean.Page;
import com.automannn.common.web.bean.ResultBean;
import com.automannn.common.web.util.ServiceLocator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:34
 */
public class BaseControllerImpl implements IBaseController{
    protected static final String USER_ID = "userId";

    protected static final String RESULT = "result";
    protected static final String PAGE_BEAN = "pageBean";

    public BaseControllerImpl() {
    }

    public ResultBean result() {
        ResultBean resultBean = (ResultBean)this.getRequest().getAttribute("_WEB_RESULT_BEAN");
        if (null == resultBean) {
            resultBean = (ResultBean) ServiceLocator.getAppContext().getBean(ResultBean.class);
            this.getRequest().setAttribute("_WEB_RESULT_BEAN", resultBean);
        }

        return resultBean;
    }

    public void setData(String fieldId, Object value) {
        ResultBean resultBean = this.result();
        resultBean.addField(fieldId, value);
    }

    public void setData(Map<String, Object> data, boolean clear) {
        ResultBean resultBean = this.result();
        resultBean.setData(data, clear);
    }

    public void setError(String errorMsg) {
        this.setSuccess(false);
        this.setError("418", errorMsg);
    }

    public void setError(String errorId, String errorMsg) {
        this.setSuccess(false);
        this.setObjErrors(errorId, errorMsg);
    }

    public void setErrors(String[] errorMsgs) {
        this.setSuccess(false);
        this.setErrors("418", errorMsgs);
    }

    public void setErrors(String errorId, String[] errorMsgs) {
        this.setObjErrors(errorId, errorMsgs);
    }

    public void setObjErrors(String errorId, Object errorMsgs) {
        ResultBean resultBean = this.result();
        if (null != errorId && null != errorMsgs) {
            if (errorMsgs instanceof String[]) {
                String[] var4 = (String[])((String[])errorMsgs);
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String errorMsg = var4[var6];
                    resultBean.addError(errorId, errorMsg);
                }
            } else {
                resultBean.addError(errorId, (String)errorMsgs);
            }
        }

    }

    public void setValidError(String errorCode, String parameter, String errorMsg) {
        ResultBean resultBean = this.result();
        resultBean.addError(errorCode, parameter, errorMsg);
    }

    public void setPageBean(Page<?> page) {
        this.result().addField("pageBean", page);
    }

    public void setSuccess(boolean success) {
        this.result().setSuccess(success);
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return this.getRequest().getSession();
    }


    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public MultipartHttpServletRequest getMultipartHttpServletRequest(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        return multipartResolver.isMultipart(request) ? (MultipartHttpServletRequest)request : null;
    }

    public void writeJsonToClient(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String getCurUserId() {
        return (String) this.getSession().getAttribute(USER_ID);
    }

}
