package com.wz.poc.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * VideoConfig
 *
 * @Author: "wangmingfei"
 * @Date: 2020/5/11 13:51
 * @Description: 阿里云视频点播用户配置
 */
@ConfigurationProperties(prefix = "aliyun.audio")
@Data
@Component(value = "ossConfig")
public class OssConfig {

    private String bucketName;

    private String endpoint;

    private String accessKeyID;

    private String accessKeySecret;

    private String cdnUrl;

}
