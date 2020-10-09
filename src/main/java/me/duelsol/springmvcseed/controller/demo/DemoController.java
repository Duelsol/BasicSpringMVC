package me.duelsol.springmvcseed.controller.demo;

import me.duelsol.springmvcseed.framework.security.AccessTokenManager;
import me.duelsol.springmvcseed.framework.support.ResponseData;
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
    public ResponseData login() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return new ResponseData(AccessTokenManager.generate());
    }

    @PostMapping(value = "logout")
    @ResponseBody
    public ResponseData logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseData("Logout successful.");
    }

    @RequestMapping(value = "list")
    @ResponseBody
    @Cacheable(value = "default", key = "'allDemos'")
    public ResponseData list() {
        return new ResponseData(demoService.selectAllDemos());
    }

    @RequestMapping(value = "create")
    @ResponseBody
    @CachePut(value = "default", key = "'allDemos'")
    public ResponseData create() {
        demoService.createDemo(8, "no detail");
        return new ResponseData(demoService.selectAllDemos());
    }

    @RequestMapping(value = "websocket")
    @ResponseBody
    public void websocket() {
        WebSocketCenter.sendMessage(demoWebSocketHandler, new TextMessage("testWebSocket"));
    }

}