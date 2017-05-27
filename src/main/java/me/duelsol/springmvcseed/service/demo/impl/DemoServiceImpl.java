package me.duelsol.springmvcseed.service.demo.impl;

import me.duelsol.springmvcseed.entity.demo.DemoEntity;
import me.duelsol.springmvcseed.framework.messagequeue.MessageQueueConsumer;
import me.duelsol.springmvcseed.framework.messagequeue.MessageQueueProducer;
import me.duelsol.springmvcseed.service.BaseService;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-30
 * Time: 23:37
 */
@Service
public class DemoServiceImpl extends BaseService implements DemoService {

    @Override
    public List<Map> selectAllDemos() throws SQLException {
        return daoFactory.getDemoDao().selectAllDemos();
    }

    @Override
    public DemoEntity getDemoById(final int id) throws SQLException {
        return daoFactory.getDemoDao().getDemoByID(id);
    }

    @Override
    public void saveDemos() {
        for (int i = 0; i < 10; i++) {
            DemoEntity entity = new DemoEntity();
            entity.setAmount(i);
            entity.setDetail("index:" + i);
            entity.save();
        }
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
