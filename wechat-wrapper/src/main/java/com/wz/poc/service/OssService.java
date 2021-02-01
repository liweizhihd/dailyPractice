package com.wz.poc.service;

import java.io.File;
import java.io.InputStream;

/**
 * OssService
 *
 * @Author: "wangmingfei"
 * @Date: 2020/9/9 11:21
 * @Description: Oss业务处理类
 */
public interface OssService {

    String uploadFile(File file);

    String uploadInputStream(String objectName, InputStream inputStream);
}
