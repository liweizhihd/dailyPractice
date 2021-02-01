package com.wz.poc.audio2text.model;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public class ResponseData {
    private int code;
    private String message;
    private String sid;
    private Data data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSid() {
        return sid;
    }

    public Data getData() {
        return data;
    }
}
