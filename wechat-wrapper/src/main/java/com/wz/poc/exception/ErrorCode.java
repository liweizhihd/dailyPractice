package com.wz.poc.exception;

/**
 * ErrorCode
 *
 * @Author: "wangmingfei"
 * @Date: 2020/5/13 16:16
 * @Description: 错误码枚举类
 */
public enum ErrorCode {

    //调用服务服务异常
    SERVICE_CALL_EXCEPTION(1004, "SERVICE_CALL_EXCEPTION"),
    //通用异常
    QCODE_EXCEPTION(1001, "QCODE_EXCEPTION"),
    //验证码异常
    CAPTCHA_EXCEPTION(1003, "CAPTCHA_EXCEPTION"),
    //未知异常
    UNKNOWN_EXCEPTION(1500, "UNKNOWN_EXCEPTION");

    private int code;

    private String value;

    ErrorCode(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}