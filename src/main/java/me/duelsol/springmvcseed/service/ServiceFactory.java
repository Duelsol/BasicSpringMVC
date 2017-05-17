package me.duelsol.springmvcseed.service;

import me.duelsol.springmvcseed.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/31
 * Time: 11:40
 */
@Service
public final class ServiceFactory {

    @Autowired
    private DemoService demoService;

    public DemoService getDemoService() {
        return demoService;
    }

}
