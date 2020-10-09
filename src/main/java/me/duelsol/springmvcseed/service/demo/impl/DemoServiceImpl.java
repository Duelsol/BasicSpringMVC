package me.duelsol.springmvcseed.service.demo.impl;

import me.duelsol.springmvcseed.dao.DemoMapper;
import me.duelsol.springmvcseed.model.Demo;
import me.duelsol.springmvcseed.model.DemoExample;
import me.duelsol.springmvcseed.service.BaseService;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-30
 * Time: 23:37
 */
@Service
public class DemoServiceImpl extends BaseService implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> selectAllDemos() {
        return demoMapper.selectByExample(new DemoExample());
    }

    @Override
    public Demo getDemoById(final int id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Demo createDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);
        demoMapper.insert(demo);
        return demo;
    }

}
