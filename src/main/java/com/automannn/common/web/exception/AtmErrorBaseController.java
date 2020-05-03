package com.automannn.common.web.exception;

import com.automannn.common.web.annotation.AtmController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author automannn@163.com
 * @time 2020/5/3 20:47
 */
@AtmController({"${server.error.path:${error.path:/error}}"})
public class AtmErrorBaseController implements ErrorController {
    private AtmErrorResolver atmErrorResolver;
    private ServerProperties serverProperties;

    public AtmErrorBaseController(AtmErrorResolver atmErrorResolver,ServerProperties serverProperties) {
        this.atmErrorResolver = atmErrorResolver;
        this.serverProperties = serverProperties;
    }

    @Override
    public String getErrorPath() {
        return this.getErrorProperties().getPath();
    }

    @RequestMapping
    public void error(HttpServletRequest request) {
        this.atmErrorResolver.resolveException(request, this.getErrorProperties());
    }

    protected ErrorProperties getErrorProperties() {
        return this.serverProperties.getError();
    }
}
