package com.automannn.common.web.interceptor;

import com.automannn.common.web.bean.ResultBean;
import com.automannn.common.web.util.ServiceLocator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author automannn@163.com
 * @time 2020/5/3 19:42
 */
public class ResultBeanInterceptor implements AtmHandlerInterceptor{
    public ResultBeanInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ResultBean resultBean = (ResultBean) ServiceLocator.getAppContext().getBean(ResultBean.class);
        resultBean.setRequestId(UUID.randomUUID().toString());
        request.setAttribute("_WEB_RESULT_BEAN", resultBean);
        return true;
    }
}
