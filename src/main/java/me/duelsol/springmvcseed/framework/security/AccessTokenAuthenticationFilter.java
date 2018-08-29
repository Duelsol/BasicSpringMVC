package me.duelsol.springmvcseed.framework.security;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2018/8/29
 * Time: 17:45
 */
public class AccessTokenAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String accessToken = AccessTokenManager.get((HttpServletRequest) request);
        return AccessTokenManager.validate(accessToken);
    }

}
