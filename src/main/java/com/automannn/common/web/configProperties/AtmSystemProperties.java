package com.automannn.common.web.configProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author automannn@163.com
 * @time 2020/5/3 20:58
 */
@Component
@ConfigurationProperties(prefix = "atm")
public class AtmSystemProperties {

    private String interErrorMsg = "出现了点小意外,请稍后再试或联系管理员";
    private AtmSystemProperties.ApplicationInfo application = new AtmSystemProperties.ApplicationInfo();
    private boolean internalErrorResolver = true;

    public AtmSystemProperties() {
    }

    public String getInterErrorMsg() {
        return this.interErrorMsg;
    }

    public void setInterErrorMsg(String interErrorMsg) {
        this.interErrorMsg = interErrorMsg;
    }

    public boolean isInternalErrorResolver() {
        return this.internalErrorResolver;
    }

    public void setInternalErrorResolver(boolean internalErrorResolver) {
        this.internalErrorResolver = internalErrorResolver;
    }

    public AtmSystemProperties.ApplicationInfo getApplication() {
        return this.application;
    }

    public void setApplication(AtmSystemProperties.ApplicationInfo application) {
        this.application = application;
    }

    static class ApplicationInfo implements Serializable {
        private static final long serialVersionUID = -1741199874923319476L;
        private String version;
        private String name;

        ApplicationInfo() {
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
