package me.duelsol.springmvcseed.framework.token;

import me.duelsol.springmvcseed.framework.token.annotation.Token;
import me.duelsol.springmvcseed.framework.token.annotation.TokenBehaviour;
import org.springframework.web.bind.annotation.ResponseBody;
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
            Token tokenAnnotation = method.getAnnotation(Token.class);
            if (tokenAnnotation != null) {
                // TokenBehaviour.create时不需要验证token。
                TokenBehaviour behaviour = tokenAnnotation.behaviour();
                if (behaviour != TokenBehaviour.CREATE) {
                    TokenManagerDelegate tokenDelegate = TokenManager.getDelegate();
                    String token = tokenDelegate.get(request);
                    boolean check = tokenDelegate.validate(token);
                    if (!check) {
                        return false;
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
            Token tokenAnnotation = method.getAnnotation(Token.class);
            if (tokenAnnotation != null) {
                TokenManagerDelegate delegate = TokenManager.getDelegate();
                String token = delegate.get(request);
                // TokenBehaviour.remove或TokenBehaviour.refresh时，移除token。
                TokenBehaviour behaviour = tokenAnnotation.behaviour();
                if (behaviour == TokenBehaviour.REMOVE || behaviour == TokenBehaviour.REFRESH) {
                    delegate.remove(token, request);
                }
                // TokenBehaviour.create或TokenBehaviour.refresh时，生成token。
                if (behaviour == TokenBehaviour.CREATE || behaviour == TokenBehaviour.REFRESH) {
                    ResponseBody rbAnnotation = method.getAnnotation(ResponseBody.class);
                    if (rbAnnotation != null) {
                        token = delegate.generate();
                        delegate.put(token, response);
                    }
                }
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
