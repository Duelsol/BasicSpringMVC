package me.duelsol.springmvcseed.framework;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/7/13
 * Time: 19:52
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> DATASOURCE = new ThreadLocal<>();

    public static void setDataSource(String customerType) {
        DATASOURCE.set(customerType);
    }

    public static String getDataSource() {
        return DATASOURCE.get();
    }

}
