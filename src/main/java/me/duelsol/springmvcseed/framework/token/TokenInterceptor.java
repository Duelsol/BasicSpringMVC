package me.duelsol.springmvcseed.framework.token;

import me.duelsol.springmvcseed.framework.cache.CacheManager;
import me.duelsol.springmvcseed.framework.token.annotation.DuplicateSubmissionsChecker;
import me.duelsol.springmvcseed.framework.token.annotation.DuplicateSubmissionsSource;
import me.duelsol.springmvcseed.framework.token.annotation.Token;
import me.duelsol.springmvcseed.framework.token.annotation.TokenBehaviour;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
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
                    /*
                    DuplicateSubmissionsChecker用来防止重复提交，
                    被注解的方法会将request中的token和缓存中的token进行比较，相同则表示本次是首次请求。
                    接着移除缓存中的token，这样下次重复提交的请求会因为缓存中无token而不通过。
                    缓存中的token由被DuplicateSubmissionsSource注解的方法放入。
                    这两个Annotation的key应当相同并保证其在系统中的唯一性。
                    */
                    DuplicateSubmissionsChecker dscAnnotation = method.getAnnotation(DuplicateSubmissionsChecker.class);
                    if (dscAnnotation != null) {
                        Cache cacheDelegate = CacheManager.getDelegate();
                        String key = dscAnnotation.key() + "_" + request.getSession().getId();
                        Cache.ValueWrapper wrapper = cacheDelegate.get(key);
                        String cachedToken = wrapper != null ? wrapper.get().toString() : null;
                        if (token == null || cachedToken == null) {
                            return false;
                        } else if (!token.equals(cachedToken)) {
                            return false;
                        }
                        cacheDelegate.evict(key);
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
                // DuplicateSubmissionsSource是防止重复提交的前置条件，所注解的方法会将token放入缓存。
                DuplicateSubmissionsSource dssAnnotation = method.getAnnotation(DuplicateSubmissionsSource.class);
                if (dssAnnotation != null && behaviour != TokenBehaviour.REMOVE) {
                    String key = dssAnnotation.key() + "_" + request.getSession().getId();
                    if (StringUtils.isNotBlank(token)) {
                        CacheManager.getDelegate().put(key, token);
                    }
                }
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
