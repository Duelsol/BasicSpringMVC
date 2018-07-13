package me.duelsol.springmvcseed.service.demo;

import me.duelsol.springmvcseed.model.Demo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-31
 * Time: 0:01
 */
public interface DemoService {

    List<Demo> selectAllDemos();

    Demo getDemoById(final int id);

    Demo saveDemo(int amount, String detail);

    void testMessageQueue() throws IOException, TimeoutException;

}
