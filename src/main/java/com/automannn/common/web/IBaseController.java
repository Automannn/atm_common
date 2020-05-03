package com.automannn.common.web;

import com.automannn.common.web.bean.Page;
import com.automannn.common.web.bean.ResultBean;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:11
 */
public interface IBaseController {
    void setData(String fieldId, Object value);

    void setData(Map<String, Object> data, boolean clear);

    void setPageBean(Page<?> page);

    ResultBean result();

    void setSuccess(boolean success);

    HttpServletRequest getRequest();

    HttpSession getSession();

    HttpServletResponse getResponse();

    MultipartHttpServletRequest getMultipartHttpServletRequest(HttpServletRequest request);

    void writeJsonToClient(Object obj) throws IOException;

    String getCurUserId();

}
