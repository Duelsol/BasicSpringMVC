package me.duelsol.springmvcseed.service.token;

import me.duelsol.springmvcseed.framework.token.TokenManagerDelegate;
import me.duelsol.springmvcseed.service.BaseService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/17
 * Time: 16:25
 */
public class SessionAdapter extends BaseService implements TokenManagerDelegate {

    @Override
    public boolean check(String token) {
        String serverToken = stringRedisTemplate.opsForValue().get("token");
        if (serverToken == null || token == null) {
            return false;
        } else if (!StringUtils.equals(serverToken, token)) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        // 这里的UUID只是作为例子，实际中根据需要自己构造token并加密，形如"用户ID+过期时间+其他"等。
        return UUID.randomUUID().toString();
    }

    @Override
    public void set(String token, HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).setAttribute("token", token);
    }

    @Override
    public String get(HttpServletRequest request) {
        return (String) request.getSession(false).getAttribute("token");
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).removeAttribute("token");
    }

}
