package me.duelsol.springmvcseed.framework.messagequeue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/4
 * Time: 20:54
 */
public class MessageQueueConsumer extends RabbitEndPoint implements Runnable, Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageQueueConsumer.class);

    public MessageQueueConsumer(final String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
    }

    @Override
    public void handleCancelOk(String consumerTag) {
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String queueName = envelope.getRoutingKey();
        String message = SerializationUtils.deserialize(body);
        LOGGER.info("queueName: {}, message: {}", queueName, message);
    }

    @Override
    public void run() {
        try {
            // start consuming messages. Auto acknowledge messages.
            super.channel.basicConsume(super.queueName, true, this);
        } catch (IOException e) {
            LOGGER.error("消息队列获取时发生错误,queueName=" + super.queueName, e);
        }
    }

}
