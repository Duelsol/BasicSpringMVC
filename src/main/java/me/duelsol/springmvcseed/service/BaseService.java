package me.duelsol.springmvcseed.service;

import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/31
 * Time: 11:35
 */
public abstract class BaseService {

    @Resource(name = "taskExecutor")
    protected TaskExecutor taskExecutor;

}
