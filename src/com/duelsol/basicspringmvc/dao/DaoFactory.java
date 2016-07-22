package com.duelsol.basicspringmvc.dao;

import com.duelsol.basicspringmvc.dao.demo.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/30
 * Time: 23:16
 */
@Repository
public class DaoFactory {

    @Autowired
    private DemoDao demoDao;

    public DemoDao getDemoDao() {
        return demoDao;
    }

}
