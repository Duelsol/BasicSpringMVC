package me.duelsol.springmvcseed.framework.listener;

import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/11/7
 * Time: 17:43
 */
public class Log4j2ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Configurator.initialize(null, "config/log4j2.xml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
