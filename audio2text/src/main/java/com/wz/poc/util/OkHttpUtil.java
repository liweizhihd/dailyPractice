package com.wz.poc.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
@Slf4j
public class OkHttpUtil {
    public static OkHttpClient client = new OkHttpClient();

    public static Response getResponseNotClose(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            return OkHttpUtil.client.newCall(request).execute();
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static InputStream getByteStream(String url) {

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                log.error("response is null or empty body:{}", response.toString());
                throw new RuntimeException("empty response");
            }
            return response.body().byteStream();
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
