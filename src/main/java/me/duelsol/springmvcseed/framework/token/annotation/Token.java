package me.duelsol.springmvcseed.framework.token.annotation;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/10
 * Time: 21:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    TokenBehaviour behaviour() default TokenBehaviour.DEFAULT;

}
