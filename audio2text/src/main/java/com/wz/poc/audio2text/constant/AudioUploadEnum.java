package com.wz.poc.audio2text.constant;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public enum AudioUploadEnum {
    /**
     * mp3格式
     */
    MP3_16K(1280, 40, "audio/L16;rate=16000", "lame");

    AudioUploadEnum(int frameSize, int intervel, String format, String encoding) {
        this.frameSize = frameSize;
        this.intervel = intervel;
        this.format = format;
        this.encoding = encoding;
    }

    public int getFrameSize() {
        return frameSize;
    }

    public int getIntervel() {
        return intervel;
    }

    public String getFormat() {
        return format;
    }

    public String getEncoding() {
        return encoding;
    }

    /**
     * 每一帧音频的大小,单位: B
     */
    private int frameSize;
    /**
     * 发送间隔,单位: ms
     */
    private int intervel;

    /**
     *
     */
    private String format;
    /**
     *
     */
    private String encoding;

}
