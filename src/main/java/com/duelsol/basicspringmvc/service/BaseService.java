package com.duelsol.basicspringmvc.service;

import com.duelsol.basicspringmvc.dao.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/31
 * Time: 11:35
 */
public class BaseService {

    @Autowired
    protected DaoFactory daoFactory;

}
