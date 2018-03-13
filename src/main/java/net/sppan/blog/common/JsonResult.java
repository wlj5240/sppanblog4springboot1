package net.sppan.blog.common;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 返回值封装，常用于业务层需要多个返回值
 */
public class JsonResult {

    private int code = 0;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static JsonResult ok() {
        JsonResult result = new JsonResult();
        result.setCode(0);
        return result;
    }

    public static JsonResult ok(String message, Map<String, Object> data) {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static JsonResult fail() {
        JsonResult result = new JsonResult();
        result.setCode(-1);
        return result;
    }

    public static JsonResult fail(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(-1);
        result.setMessage(msg);
        return result;
    }

    public JsonResult setMapData(String key, Object value) {
        Object data = this.getData();
        if (data != null && data instanceof Map) {
            ((Map) data).put(key, value);
        } else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(key, value);
            setData(hashMap);
        }
        return this;
    }
}


