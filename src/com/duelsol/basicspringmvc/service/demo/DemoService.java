package com.duelsol.basicspringmvc.service.demo;

import com.duelsol.basicspringmvc.entity.demo.DemoEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-31
 * Time: 0:01
 */
public interface DemoService {

    public List<Map> findAllAccounts() throws SQLException;

    public DemoEntity getDemoById(int id) throws SQLException;

    public void saveDemos();

}
