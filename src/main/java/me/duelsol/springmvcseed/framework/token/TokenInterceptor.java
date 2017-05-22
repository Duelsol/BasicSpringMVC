package me.duelsol.springmvcseed.framework.token;

import me.duelsol.springmvcseed.framework.token.annotation.Token;
import me.duelsol.springmvcseed.framework.token.annotation.TokenBehaviour;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/10
 * Time: 21:08
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                TokenBehaviour behaviour = annotation.behaviour();
                // TokenBehaviour.create时不需要验证token。
                if (behaviour != TokenBehaviour.create) {
                    String token = TokenManager.getDelegate().get(request);
                    boolean check = TokenManager.getDelegate().check(token);
                    if (!check) {
                        return false;
                    }
                    // TokenBehaviour.remove或TokenBehaviour.refresh时，移除token。
                    if (behaviour == TokenBehaviour.remove || behaviour == TokenBehaviour.refresh) {
                        TokenManager.getDelegate().remove(request, response);
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex == null && handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                TokenBehaviour behaviour = annotation.behaviour();
                // TokenBehaviour.create或TokenBehaviour.refresh时，生成token。
                if (behaviour == TokenBehaviour.create || behaviour == TokenBehaviour.refresh) {
                    String token = TokenManager.getDelegate().generate();
                    TokenManager.getDelegate().set(token, request, response);
                }
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
