package com.duelsol.basicspringmvc.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 15/1/3
 * Time: 12:34
 */
@RemoteProxy(name = "DWRMessenger")
public class DWRMessenger {

    private static Logger logger = LogManager.getLogger(DWRMessenger.class);

    @RemoteMethod
    public Object call(String className, String methodName, Map<String, String> map) throws Exception {
        Class clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ignored) {
            throw new ClassNotFoundException("类不存在:" + className);
        }
        Method method;
        try {
            Class[] classes = new Class[]{Map.class};
            method = clazz.getMethod(methodName, classes);
        } catch (Exception ignored) {
            throw new RuntimeException("方法不存在或传入参数不匹配:" + methodName);
        }
        try {
            return method.invoke(clazz.newInstance(), map);
        } catch (Exception e) {
            logger.error("DWR调用失败", e);
            throw e;
        }
    }

}
