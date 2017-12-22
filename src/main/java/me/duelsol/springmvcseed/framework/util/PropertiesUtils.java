package me.duelsol.springmvcseed.framework.util;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/5
 * Time: 16:06
 */
public class PropertiesUtils {

    private static final Properties properties = new Properties();

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    static {
        try {
            Resource resource = new ClassPathResource("config/properties");
            Collection files = FileUtils.listFiles(resource.getFile(), new String[]{"properties"}, true);
            for (Object file : files) {
                if (file != null && file instanceof File) {
                    properties.load(new FileInputStream((File) file));
                }
            }
        } catch (IOException e) {
            LOGGER.error("PropertiesUtils初始化时发生错误", e);
        }
    }

    private PropertiesUtils() {}

    public static String getProperty(@NotNull String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
