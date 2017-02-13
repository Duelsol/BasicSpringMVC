package me.duelsol.springmvcseed.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-29
 * Time: 22:36
 */
public class BaseDao {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

}
