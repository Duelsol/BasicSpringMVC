package me.duelsol.springmvcseed.framework.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 00:12
 */
public interface TokenManagerDelegate {

    /**
     * 生成token。
     */
    String generate();

    /**
     * 验证token。
     *
     * @return 验证通过返回true，否则返回false。
     */
    boolean validate(String token);

    /**
     * 将token置入request或response。
     */
    void position(String token, HttpServletRequest request, HttpServletResponse response);

    /**
     * 缓存token.
     */
    void cache(String key, String token);

    /**
     * 获取token。
     */
    String getToken(HttpServletRequest request);

    /**
     * 从缓存中获取token。
     */
    String getCachedToken(String key);

    /**
     * 移除token。
     */
    void removeToken(HttpServletRequest request);

    /**
     * 从缓存中移除token。
     */
    void removeCachedToken(String key);

}
