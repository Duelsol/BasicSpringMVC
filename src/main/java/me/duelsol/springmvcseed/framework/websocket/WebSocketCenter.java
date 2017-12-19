package me.duelsol.springmvcseed.framework.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketMessage;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 2017/12/19
 * Time: 11:23
 */
public class WebSocketCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketCenter.class);

    private WebSocketCenter() {}

    public static void sendMessage(AbstractWebSocketHandler handler, WebSocketMessage<?> message) {
        try {
            handler.sendMessage(message);
        } catch (IOException e) {
            LOGGER.error("WebSocketCenter发送消息时报错，class =" + handler.getClass().getCanonicalName(), e);
        }
    }

}
