package com.wz.poc.model.vo;

import com.wz.poc.model.constant.StateCode;
import lombok.Builder;
import lombok.Data;

/**
 * @author liweizhi
 * @date 2021/1/8
 */
@Data
@Builder
public class ResultWrapper<T> {
    private Integer code;

    private String msg;

    private T data;

    /**
     * 返回失败vo
     */
    public static <E> ResultWrapper<E> getSuccessResultView(E data) {
        return ResultWrapper.<E>builder().code(StateCode.SUCCESS.code()).msg(StateCode.SUCCESS.msg()).data(data).build();
    }

    /**
     * 返回成功vo
     */
    public static <E> ResultWrapper<E> getErrorResultView(E data) {
        return ResultWrapper.<E>builder().code(StateCode.ERROR.code()).data(data).build();
    }
}
