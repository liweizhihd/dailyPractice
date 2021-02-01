package com.wz.poc.audio2text.constant;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public class Audio2textConstant {
    private Audio2textConstant() {
    }

    /**
     * 发送第一帧音频status = 0
     */
    public static final int STATUS_FIRST_FRAME = 0;
    /**
     * 发送中间帧status = 1
     */
    public static final int STATUS_CONTINUE_FRAME = 1;
    /**
     * 最后一帧音频status = 2 ，标志音频发送结束
     */
    public static final int STATUS_LAST_FRAME = 2;


    public static final String FIELD_STATUS = "status";
    public static final String FIELD_FORMAT = "format";
    public static final String FIELD_ENCODING = "encoding";
    public static final String FIELD_AUDIO = "audio";
}
