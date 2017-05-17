package me.duelsol.springmvcseed.framework.listener;

import me.duelsol.springmvcseed.framework.token.TokenManager;
import me.duelsol.springmvcseed.service.token.JWTAdapter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/15
 * Time: 17:12
 */
public class TokenConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TokenManager.initialize(JWTAdapter.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
