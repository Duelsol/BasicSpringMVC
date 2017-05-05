package me.duelsol.springmvcseed.framework.messagequeue;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/4
 * Time: 20:55
 */
public class MessageQueueProducer extends RabbitEndPoint {

    public MessageQueueProducer(String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    public void sendMessage(Serializable object) throws IOException, TimeoutException {
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }

}
