package me.duelsol.springmvcseed.framework.cache;

import me.duelsol.springmvcseed.framework.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cache.Cache;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/27
 * Time: 20:35
 */
public final class CacheManager {

    private static Cache DELEGATE = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);

    static {
        try {
            DELEGATE = ApplicationContextHolder.getInstance().getBean(RedisCacheImpl.class);
        } catch (BeansException e) {
            LOGGER.error("CacheManager代理初始化时从Spring上下文中获取RedisCacheImpl发生错误", e);
        }
    }

    private CacheManager() {}

    public static Cache getDelegate() {
        return DELEGATE;
    }

}
