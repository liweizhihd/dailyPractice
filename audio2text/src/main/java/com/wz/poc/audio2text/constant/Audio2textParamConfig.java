package com.wz.poc.audio2text.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
@Component
@ConfigurationProperties(prefix = "audio2text")
@Data
public class Audio2textParamConfig {
    /**
     * 中英文，http url 不支持解析 ws/wss schema
     */
    private String hostUrl;
    /**
     * 在控制台-我的应用获取
     */
    private String appid;
    /**
     * 在控制台-我的应用-语音听写（流式版）获取
     */
    private String apiSecret;
    /**
     * 在控制台-我的应用-语音听写（流式版）获取
     */
    private String apiKey;
}
