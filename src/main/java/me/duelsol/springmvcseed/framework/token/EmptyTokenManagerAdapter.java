package me.duelsol.springmvcseed.framework.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/17
 * Time: 10:35
 */
public class EmptyTokenManagerAdapter implements TokenManagerDelegate {

    @Override
    public String generate() {
        return null;
    }

    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public void position(String token, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public String getToken(HttpServletRequest request) {
        return null;
    }

    @Override
    public void removeToken(HttpServletRequest request) {
    }

}
