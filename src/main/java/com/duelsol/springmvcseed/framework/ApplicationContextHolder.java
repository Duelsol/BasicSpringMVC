package com.duelsol.springmvcseed.framework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/11/1
 * Time: 07:16
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext instance;

    private ApplicationContextHolder() {}

    public static ApplicationContext getInstance() {
        return instance;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        instance = applicationContext;
    }

}
