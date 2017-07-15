package me.duelsol.springmvcseed.framework;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/7/13
 * Time: 19:56
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

}
