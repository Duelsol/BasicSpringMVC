package me.duelsol.springmvcseed.service.token;

import me.duelsol.springmvcseed.framework.token.TokenManagerDelegate;
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
public class SessionAdapter implements TokenManagerDelegate {

    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        String clientToken = request.getParameter("token");
        if (serverToken == null || clientToken == null) {
            return false;
        } else if (!StringUtils.equals(serverToken, clientToken)) {
            return false;
        }
        return true;
    }

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) {
        // 这里的UUID只是作为例子，实际中根据需要自己构造token并加密，形如"用户ID+过期时间+其他"等。
        request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).removeAttribute("token");
    }

}
