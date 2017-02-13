package com.duelsol.springmvcseed.controller.demo;

import com.duelsol.springmvcseed.controller.BaseController;
import com.duelsol.springmvcseed.entity.demo.DemoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DemoController extends BaseController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        return "index";
	}

    @RequestMapping(value = "/demo")
    @ResponseBody
    public Object findAllAccounts() throws SQLException {
        List<Map> querys = serviceFactory.getDemoService().findAllAccounts();
        DemoEntity entity = serviceFactory.getDemoService().getDemoById(1);
        if (entity == null) {
            entity = new DemoEntity();
        }
        entity.setAmount(querys.size());
        entity.setDetail("demo");
        entity.save();
        return entity;
    }

}