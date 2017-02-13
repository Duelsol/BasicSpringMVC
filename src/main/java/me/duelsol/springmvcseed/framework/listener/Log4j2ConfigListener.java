package me.duelsol.springmvcseed.framework.listener;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/11/7
 * Time: 17:43
 */
public class Log4j2ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/log4j2.xml");
        try {
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
        } catch (IOException e) {
            System.out.println("log4j2配置文件读取失败");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
