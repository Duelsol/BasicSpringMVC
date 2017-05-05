package me.duelsol.springmvcseed.controller.demo;

import me.duelsol.springmvcseed.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/")
public class DemoController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        try {
            serviceFactory.getDemoService().testMessageQueue();
        } catch (IOException | TimeoutException e) {
            LOGGER.error("启动消息队列时发生错误", e);
        }
        model.addAttribute("message", "Hello world!");
        return "index";
	}

    @RequestMapping(value = "/demo")
    @ResponseBody
    public Object findAllAccounts() {
        try {
            return serviceFactory.getDemoService().findAllAccounts();
        } catch (SQLException e) {
            LOGGER.error("获取所有账户时发生错误", e);
        }
        return null;
    }

}