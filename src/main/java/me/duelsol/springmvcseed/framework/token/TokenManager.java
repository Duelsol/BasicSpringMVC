package me.duelsol.springmvcseed.framework.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 00:02
 */
public final class TokenManager {

    /**
     * Token代理类实例，项目启动时在listener中初始化。
     * @see me.duelsol.springmvcseed.framework.listener.TokenConfigListener
     */
    private static TokenDelegate instance = null;

    /**
     * 默认实现的Token代理类，方法全部为空，check返回不通过。
     * @see EmptyTokenAdapter
     */
    private static final TokenDelegate emptyTokenAdapter = new EmptyTokenAdapter();

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);

    private TokenManager() {}

    public static void initialize(final Class<? extends TokenDelegate> delegate) {
        if (instance == null) {
            try {
                instance = delegate.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("TokenManager初始化时发生错误", e);
            }
        }
    }

    public static TokenDelegate getDelegate() {
        if (instance == null) {
            LOGGER.info("TokenManager的初始化方法尚未被调用，返回默认的空代理类。");
            return emptyTokenAdapter;
        }
        return instance;
    }

}
