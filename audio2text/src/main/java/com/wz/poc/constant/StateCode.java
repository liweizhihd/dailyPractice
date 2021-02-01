package com.wz.poc.constant;

public enum StateCode {

    /**
     * 成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 参数出错
     */
    CONSTRAINT_VIOLATION(502, "方法参数校验出错"),
    /**
     * 参数无效
     */
    METHOD_ARGUMENT_NOT_VALID(501, "方法参数无效"),
    /**
     * 通用异常
     */
    ERROR(500, "服务器异常"),
    /**
     * 其他自定义业务异常
     */
    NO_LOGIN(100, "未登录");

    private String msg;

    private int code;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    StateCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
