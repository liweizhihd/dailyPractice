package com.wz.poc.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {

    /**
     * 异常代码
     */
    private final ErrorCode code;

    /**
     * 异常信息
     */
    private final String message;

    public MyException(String message) {
        super(message);
        this.message = message;
        this.code = ErrorCode.QCODE_EXCEPTION;
    }

    public MyException(ErrorCode code, String message) {
        super(message);
        this.message = message;
        this.code = code != null ? code : ErrorCode.QCODE_EXCEPTION;
    }

}
