package me.duelsol.springmvcseed.framework.datasource;

import me.duelsol.springmvcseed.framework.datasource.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2018/9/17
 * Time: 20:23
 */
@Aspect
@Component
public class DataSourceSetterAspect {

    @Pointcut("execution(* me.duelsol.springmvcseed.service..*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = method.getAnnotation(DataSource.class);
            DataSourceHolder.setDataSource(dataSource.value());
        }
    }

    @AfterReturning(value = "pointcut()")
    public void afterReturning() {
        DataSourceHolder.clear();
    }

}
