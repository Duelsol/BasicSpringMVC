package me.duelsol.springmvcseed.framework.exception;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/7/12
 * Time: 21:30
 */
public class UndeclaredTableException extends RuntimeException {

    public UndeclaredTableException(Class clazz) {
        super(clazz.getName() + "未包含Table注解，无法对其进行持久化操作！");
    }

}
