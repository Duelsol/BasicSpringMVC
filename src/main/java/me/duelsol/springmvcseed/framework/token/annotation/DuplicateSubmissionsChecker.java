package me.duelsol.springmvcseed.framework.token.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/24
 * Time: 20:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateSubmissionsChecker {

    /**
     * 缓存时所需要的key，调用者应保证该key的全局唯一性。
     */
    String key();

}
