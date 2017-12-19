package me.duelsol.springmvcseed.framework.websocket;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 2017/12/19
 * Time: 11:29
 */
public abstract class AbstractWebSocketHandler extends TextWebSocketHandler {

    protected static final List<WebSocketSession> SESSIONS = new ArrayList<>();

    protected AbstractWebSocketHandler() {}

    final void sendMessage(WebSocketMessage<?> message) throws IOException {
        for (WebSocketSession session : SESSIONS) {
            session.sendMessage(message);
        }
    }

}
