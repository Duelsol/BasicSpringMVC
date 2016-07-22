package com.duelsol.basicspringmvc.controller;

import com.duelsol.basicspringmvc.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/31
 * Time: 11:45
 */
public class BaseController {

    @Autowired
    protected ServiceFactory serviceFactory;

}
