package com.wz.poc.temp;

import com.wz.poc.util.MyFileUtil;
import com.wz.poc.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * @author liweizhi
 * @date 2021/1/8
 */
@Slf4j
public class DownloadVoicec {
    @Test
    void download() {
        String audioUrl = "http://qrcode.mashibing.com/2807ec8a4d2947a9b9034877f9b2e671.mp3";
        try (Response audioResp = OkHttpUtil.getResponseNotClose(audioUrl)) {
            InputStream fs = audioResp.body().byteStream();
            MyFileUtil.saveFile(fs);
        }
    }

    @Test
    void closeTest() {
        String audioUrl = "http://qrcode.mashibing.com/2807ec8a4d2947a9b9034877f9b2e671.mp3";
        Response audioResp = OkHttpUtil.getResponseNotClose(audioUrl);
        InputStream fs = audioResp.body().byteStream();
        audioResp.close();
        // debug and see: is fs closed? -> yes
        log.info("fs:{}", fs);
    }

}
