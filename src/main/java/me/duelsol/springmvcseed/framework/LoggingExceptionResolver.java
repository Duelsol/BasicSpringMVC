package me.duelsol.springmvcseed.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/7/12
 * Time: 19:43
 */
public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingExceptionResolver.class);

    @Override
    protected void logException(Exception ex, HttpServletRequest request) {
        LOGGER.error(buildLogMessage(ex, request), ex);
    }

}
