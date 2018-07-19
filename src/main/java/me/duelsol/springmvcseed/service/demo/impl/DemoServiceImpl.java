package me.duelsol.springmvcseed.service.demo.impl;

import me.duelsol.springmvcseed.dao.DemoMapper;
import me.duelsol.springmvcseed.model.Demo;
import me.duelsol.springmvcseed.framework.messagequeue.MessageQueueConsumer;
import me.duelsol.springmvcseed.framework.messagequeue.MessageQueueProducer;
import me.duelsol.springmvcseed.model.DemoExample;
import me.duelsol.springmvcseed.service.BaseService;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

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
    public Demo saveDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);
        demoMapper.insert(demo);
        return demo;
    }

    @Override
    public void testMessageQueue() throws IOException, TimeoutException {
        MessageQueueConsumer consumer = new MessageQueueConsumer("queue");
        taskExecutor.execute(consumer);

        MessageQueueProducer producer = new MessageQueueProducer("queue");
        for (int i = 0; i < 10; i++) {
            String message = "number " + i;
            producer.sendMessage(message);
        }
        producer.close();
    }

}
