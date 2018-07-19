package me.duelsol.springmvcseed.dao;

import me.duelsol.springmvcseed.model.BaseModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2018/7/18
 * Time: 20:42
 */
@Aspect
@Component
public class AutoTimeSetterAspect {

    @Before("execution(* me.duelsol.springmvcseed.dao..*Mapper.insert*(..))")
    public void beforeInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseModel) {
                Date now = new Date();
                BaseModel model = (BaseModel) arg;
                model.setCreateTime(now);
                model.setUpdateTime(now);
                break;
            }
        }
    }

    @Before("execution(* me.duelsol.springmvcseed.dao..*Mapper.batchInsert*(..))")
    public void beforeBatchInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof List) {
                List list = (List) arg;
                for (Object object : list) {
                    if (object instanceof BaseModel) {
                        Date now = new Date();
                        BaseModel model = (BaseModel) object;
                        model.setCreateTime(now);
                        model.setUpdateTime(now);
                    }
                }
                break;
            }
        }
    }

    @Before("execution(* me.duelsol.springmvcseed.dao..*Mapper.update*(..))")
    public void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseModel) {
                BaseModel model = (BaseModel) arg;
                model.setUpdateTime(new Date());
                break;
            }
        }
    }

}
