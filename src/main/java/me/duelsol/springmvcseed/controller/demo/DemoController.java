package me.duelsol.springmvcseed.controller.demo;

import me.duelsol.springmvcseed.framework.token.annotation.DuplicateSubmissionsChecker;
import me.duelsol.springmvcseed.framework.token.annotation.DuplicateSubmissionsSource;
import me.duelsol.springmvcseed.framework.token.annotation.Token;
import me.duelsol.springmvcseed.framework.token.annotation.TokenBehaviour;
import me.duelsol.springmvcseed.service.websocket.DemoWebSocketHandler;
import me.duelsol.springmvcseed.framework.websocket.WebSocketCenter;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/")
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoWebSocketHandler demoWebSocketHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "index")
	public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
	}

    @RequestMapping(value = "select")
    @ResponseBody
    @Cacheable(value = "default", key = "new String('allDemos')")
    public Object selectAllDemos() {
        try {
            return demoService.selectAllDemos();
        } catch (SQLException e) {
            LOGGER.error("获取所有账户时发生错误", e);
        }
        return null;
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Object saveDemo() {
        return demoService.saveDemo(8, "no detail");
    }

    @RequestMapping(value = "mq")
    @ResponseBody
    public void testMessageQueue() {
        try {
            demoService.testMessageQueue();
        } catch (IOException | TimeoutException e) {
            LOGGER.error("启动消息队列时发生错误", e);
        }
    }

    @PostMapping(value = "create_jwt")
    @ResponseBody
    @Token(behaviour = TokenBehaviour.CREATE)
    @DuplicateSubmissionsSource(key = "INDEX")
    public void createJWT() {
    }

    @PostMapping(value = "validate_jwt")
    @ResponseBody
    @Token
    @DuplicateSubmissionsChecker(key = "INDEX")
    public String validateJWT() {
	    return "JWT validate passed.";
    }

    @RequestMapping(value = "websocket")
    @ResponseBody
    public void testWebSocket() {
        WebSocketCenter.sendMessage(demoWebSocketHandler, new TextMessage("testWebSocket"));
    }

}