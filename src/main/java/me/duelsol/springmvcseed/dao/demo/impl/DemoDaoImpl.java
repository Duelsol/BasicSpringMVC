package me.duelsol.springmvcseed.dao.demo.impl;

import me.duelsol.springmvcseed.dao.demo.DemoDao;
import me.duelsol.springmvcseed.dao.BaseDao;
import me.duelsol.springmvcseed.entity.demo.DemoEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-30
 * Time: 23:20
 */
@Repository
public class DemoDaoImpl extends BaseDao implements DemoDao {

    @Override
    public List<Map> selectAllDemos() throws SQLException {
        return sqlSessionTemplate.selectList("demoDao.selectAllDemos");
    }

    @Override
    public DemoEntity getDemoByID(final int id) throws SQLException {
        return sqlSessionTemplate.selectOne("demoDao.getDemoByID", id);
    }

}
