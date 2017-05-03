package me.duelsol.springmvcseed.service.demo.impl;

import me.duelsol.springmvcseed.entity.demo.DemoEntity;
import me.duelsol.springmvcseed.service.BaseService;
import me.duelsol.springmvcseed.service.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14-8-30
 * Time: 23:37
 */
@Service
public class DemoServiceImpl extends BaseService implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public List<Map> findAllAccounts() throws SQLException {
        List<Map> result = daoFactory.getDemoDao().findAllAccounts();
        taskExecutor.execute(() -> LOGGER.info("total accounts size: {}", result.size()));
        return result;
    }

    @Override
    public DemoEntity getDemoById(int id) throws SQLException {
        return daoFactory.getDemoDao().getDemoByID(id);
    }

    @Override
    public void saveDemos() {
        for (int i = 0; i < 10; i++) {
            DemoEntity entity = new DemoEntity();
            entity.setAmount(i);
            entity.setDetail("index:" + i);
            entity.save();
        }
    }

}
