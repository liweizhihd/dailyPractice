package com.wz.poc.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.wz.poc.config.oss.OssConfig;
import com.wz.poc.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * OssServiceImpl
 *
 * @Author: "wangmingfei"
 * @Date: 2020/9/9 11:28
 * @Description: OSS 业务处理实现类
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Resource
    private OSS ossClient;

    @Resource
    private OssConfig ossConfig;

    @Override
    public String uploadFile(File file) {
        String fileName = getFileName(file.getName());
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), fileName, file);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return ossConfig.getCdnUrl().concat(fileName);
    }

    @Override
    public String uploadInputStream(String objectName, InputStream inputStream) {
        String fileName = getFileName(objectName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), fileName, inputStream);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return ossConfig.getCdnUrl().concat(fileName);
    }

    private String getFileName(String originalName) {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        String fileType = originalName.substring(originalName.lastIndexOf("."));
        fileName = fileName.concat(fileType);
        return fileName;
    }
}
