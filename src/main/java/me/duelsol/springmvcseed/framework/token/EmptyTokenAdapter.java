package me.duelsol.springmvcseed.framework.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/17
 * Time: 10:35
 */
public class EmptyTokenAdapter implements TokenDelegate {

    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {
    }

}
