package me.duelsol.springmvcseed.framework.support;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2018/6/6
 * Time: 19:47
 */
public class ResponseData implements Serializable {

    /**
     * 编号，非"0"时都表示发生错误。
     */
    private String code = null;

    /**
     * 消息，在错误时说明具体原因。
     */
    private String message = null;

    /**
     * 数据。
     */
    private Object data = null;

    public ResponseData() {
        this.code = "0";
    }

    public ResponseData(Object data) {
        this.code = "0";
        this.data = data;
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
