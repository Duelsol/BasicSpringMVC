package me.duelsol.springmvcseed.framework.filter;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/9
 * Time: 18:37
 */
class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

    XSSHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = super.getParameterValues(name);
        if (result != null) {
            int length = result.length;
            for (int i = 0; i < length; i++) {
                result[i] = StringEscapeUtils.escapeHtml4(result[i]);
            }
        }
        return result;
    }

}
