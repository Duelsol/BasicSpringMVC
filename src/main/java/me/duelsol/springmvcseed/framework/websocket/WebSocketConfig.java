package me.duelsol.springmvcseed.framework.websocket;

import me.duelsol.springmvcseed.service.websocket.DemoWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 2017/12/19
 * Time: 10:54
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(demoWebSocketHandler(),"/websocket/demo");
    }

    @Bean
    public WebSocketHandler demoWebSocketHandler(){
        return new DemoWebSocketHandler();
    }

}
