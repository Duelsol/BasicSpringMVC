package me.duelsol.springmvcseed.framework.messagequeue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import me.duelsol.springmvcseed.framework.util.PropertiesUtils;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/4
 * Time: 20:59
 */
abstract class RabbitEndPoint {

    Channel channel;
    Connection connection;
    String queueName;

    RabbitEndPoint(final String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;

        // Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        // hostname of your rabbitmq server
        factory.setHost(PropertiesUtils.getProperty("mq.host"));

        // getting a connection
        this.connection = factory.newConnection();

        // creating a channel
        this.channel = this.connection.createChannel();

        // declaring a queue for this channel. If queue does not exist,
        // it will be created on the server.
        this.channel.queueDeclare(queueName, false, false, false, null);
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }

}
