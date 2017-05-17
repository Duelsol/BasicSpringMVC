package me.duelsol.springmvcseed.framework.token.annotation;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/11
 * Time: 21:00
 */
public enum TokenBehaviour {

    /**
     * 默认模式，仅在方法执行前验证Token。
     */
    common,

    /**
     * 不验证Token，在方法执行结束后返回一个新建的Token。
     */
    create,

    /**
     * 在方法执行前验证Token，在方法执行结束后移除Token。
     */
    remove,

    /**
     * 在方法执行前验证Token，在方法执行结束后返回一个新建的Token。
     */
    refresh

}
