package me.duelsol.springmvcseed.framework.token;

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
     * 放置token。
     */
    void put(String token, Object target);

    /**
     * 获取token。
     */
    String get(Object target);

    /**
     * 移除token。
     */
    void remove(String token, Object target);

}
