package me.duelsol.springmvcseed.controller.demo;

import me.duelsol.springmvcseed.framework.security.AccessTokenManager;
import me.duelsol.springmvcseed.service.websocket.DemoWebSocketHandler;
import me.duelsol.springmvcseed.framework.websocket.WebSocketCenter;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
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
	public String index(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
	}

    @PostMapping(value = "login")
    @ResponseBody
    public String login() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return AccessTokenManager.generate();
    }

    @PostMapping(value = "logout")
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "Logout successful.";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    @Cacheable(value = "default", key = "'allDemos'")
    public Object list() {
        return demoService.selectAllDemos();
    }

    @RequestMapping(value = "save")
    @ResponseBody
    @CachePut(value = "default", key = "'allDemos'")
    public Object save() {
        demoService.saveDemo(8, "no detail");
        return demoService.selectAllDemos();
    }

    @RequestMapping(value = "mq")
    @ResponseBody
    public void mq() {
        try {
            demoService.testMessageQueue();
        } catch (IOException | TimeoutException e) {
            LOGGER.error("启动消息队列时发生错误", e);
        }
    }

    @RequestMapping(value = "websocket")
    @ResponseBody
    public void websocket() {
        WebSocketCenter.sendMessage(demoWebSocketHandler, new TextMessage("testWebSocket"));
    }

}