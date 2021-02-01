package com.wz.poc.controller;

import cn.hutool.core.io.IoUtil;
import com.wz.poc.exception.MyException;
import com.wz.poc.model.vo.ResultWrapper;
import com.wz.poc.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Slf4j
@Api(value = "微信授权控制器", tags = "微信授权控制器")
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "微信 获取access_token")
    @GetMapping("/getAccessToken")
    public ResultWrapper<String> getAccessToken(@RequestParam(required = false, defaultValue = "false") boolean forceRefresh) {
        try {
            String accessToken = wxMpService.getAccessToken(forceRefresh);
            return ResultWrapper.getSuccessResultView(accessToken);
        } catch (WxErrorException e) {
            throw new MyException("微信网页授权出错: " + e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "从微信下载临时素材,上传到阿里云OSS,然后返回OSS的URL")
    @GetMapping("/getMediaAndUploadOss")
    public ResultWrapper<String> getMediaAndUploadOss(String mediaId) {
        try {
            File file = wxMpService.getMaterialService().mediaDownload(mediaId);

            String url = ossService.uploadFile(file);
            return ResultWrapper.getSuccessResultView(url);
        } catch (WxErrorException e) {
            throw new MyException("微信接口出错: " + e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "微信 下载临时素材")
    @GetMapping("/getMedia")
    public ResultWrapper<String> getMedia(String mediaId) {
        try {
            File sourceFile = wxMpService.getMaterialService().mediaDownload(mediaId);
            // 转换格式
            String mp3PathTemp = "wechatmp3" + UUID.randomUUID().toString() + ".mp3";

            String destPath = "C:\\Users\\liweizhi\\Desktop\\";
            FileInputStream byteStream = IoUtil.toStream(sourceFile);
            saveFile(byteStream, destPath + sourceFile.getName());

            // 删除文件
            try {
                byteStream.close();
                Files.delete(Paths.get(mp3PathTemp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResultWrapper.getSuccessResultView("Ok");
        } catch (WxErrorException e) {
            throw new MyException("微信接口出错: " + e.getLocalizedMessage());
        }
    }

    public static void saveFile(InputStream byteStream, String dest) {
        File file = new File(dest);
        byte[] buf = new byte[2048];
        int len = 0;

        try (FileOutputStream fos = new FileOutputStream(file)) {
            while ((len = byteStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
