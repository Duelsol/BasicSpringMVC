package me.duelsol.springmvcseed.framework.token;

import me.duelsol.springmvcseed.framework.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 00:02
 */
public final class TokenManager {

    /**
     * Token代理类Class，项目启动时在listener中初始化。
     * @see me.duelsol.springmvcseed.framework.listener.TokenConfigListener
     */
    private static Class<? extends TokenManagerDelegate> storagedDelegate = null;

    /**
     * Token代理类实例，在初始化了代理类Class后，先尝试从Spring上下文中获取，如果获取失败则调用newInstance创建。
     */
    private static TokenManagerDelegate instance = null;

    /**
     * 默认实现的Token代理类，方法全部为空，check返回不通过。
     * @see EmptyTokenManagerAdapter
     */
    private static final TokenManagerDelegate defaultDelegate = new EmptyTokenManagerAdapter();

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);

    private TokenManager() {}

    public static void initialize(final Class<? extends TokenManagerDelegate> delegate) {
        if (storagedDelegate == null) {
            storagedDelegate = delegate;
        }
    }

    public static TokenManagerDelegate getDelegate() {
        if (storagedDelegate == null) {
            LOGGER.debug("TokenManager尚未初始化，返回默认的空代理类。");
            return defaultDelegate;
        }
        if (instance == null) {
            try {
                instance = ApplicationContextHolder.getInstance().getBean(storagedDelegate);
            } catch (BeansException e) {
                LOGGER.error("TokenManager代理初始化时从Spring上下文中获取Bean发生错误，className=" + storagedDelegate.getName(), e);
            }
            if (instance == null) {
                try {
                    instance = storagedDelegate.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.error("TokenManager代理初始化时调用newInstance发生错误，className=" + storagedDelegate.getName(), e);
                }
            }
        }
        return instance;
    }

}
