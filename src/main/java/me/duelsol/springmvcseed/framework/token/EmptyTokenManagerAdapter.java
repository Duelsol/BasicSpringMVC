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
    public boolean check(String token) {
        return false;
    }

    @Override
    public String generate() {
        return null;
    }

    @Override
    public void set(String token, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public String get(HttpServletRequest request) {
        return null;
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {
    }

}
