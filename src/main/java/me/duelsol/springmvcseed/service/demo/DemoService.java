package me.duelsol.springmvcseed.service.demo;

import me.duelsol.springmvcseed.entity.demo.DemoEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-31
 * Time: 0:01
 */
public interface DemoService {

    List<Map> findAllAccounts() throws SQLException;

    DemoEntity getDemoById(int id) throws SQLException;

    void saveDemos();

    void testMessageQueue() throws IOException, TimeoutException;

}
