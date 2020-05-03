package com.automannn.common.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:36
 */
@Component
public class ServiceLocator implements ApplicationContextAware {
    private static ApplicationContext appContext;
    private static final Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

    private ServiceLocator() {
    }

    public static Object getService(String key) {
        return appContext == null ? null : appContext.getBean(key);
    }

    public static boolean containsBean(String key) {
        if (appContext == null) {
            logger.error("ApplicationContext Is null When the method used [containsBean]!");
            return false;
        } else {
            return appContext.containsBean(key);
        }
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    public static <T> T getService(Class<T> clazz) {
        return appContext == null ? null : appContext.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext() {
        return appContext;
    }

    public void setApplicationContext(@NonNull ApplicationContext ac) {
        this.initContext(ac);
    }

    private void initContext(ApplicationContext appContext) {
        ServiceLocator.appContext = appContext;
    }

}
