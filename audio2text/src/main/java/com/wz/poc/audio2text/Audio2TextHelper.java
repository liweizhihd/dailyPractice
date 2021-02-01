package com.wz.poc.audio2text;

import com.wz.poc.audio2text.constant.Audio2textParamConfig;
import com.wz.poc.audio2text.listener.IatWsListener;
import com.wz.poc.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
@Slf4j
@Component
public class Audio2TextHelper {
    @Resource
    private Audio2textParamConfig config;

    private static OkHttpClient client = new OkHttpClient.Builder().build();

    public String convert(String audioUrl) {
        // 构建鉴权url
        String authUrl = getAuthUrl(config.getHostUrl(), config.getApiKey(), config.getApiSecret());

        //将url中的 schema http://和https://分别替换为ws:// 和 wss://
        String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();

        LinkedBlockingQueue<String> blockingDeque = BlockingQueuePool.get();
        try {
            client.newWebSocket(request, new IatWsListener(config, audioUrl, blockingDeque));
            return blockingDeque.poll(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("", e);
            throw new MyException(e.getMessage());
        } finally {
            BlockingQueuePool.put(blockingDeque);
        }
    }

    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) {
        URL url = null;
        try {
            url = new URL(hostUrl);
        } catch (MalformedURLException e) {
            log.error("", e);
            throw new MyException(e.getMessage());
        }
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        StringBuilder builder = new StringBuilder("host: ").append(url.getHost()).append("\n").
                append("date: ").append(date).append("\n").
                append("GET ").append(url.getPath()).append(" HTTP/1.1");

        Charset charset = StandardCharsets.UTF_8;
        Mac mac = null;
        try {
            mac = Mac.getInstance("hmacsha256");
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
            mac.init(spec);
        } catch (Exception e) {
            log.error("", e);
            throw new MyException(e.getMessage());
        }
        byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
        String sha = Base64.getEncoder().encodeToString(hexDigits);

        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);

        HttpUrl httpUrl = HttpUrl.parse("https://" + url.getHost() + url.getPath()).newBuilder().
                addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(charset))).
                addQueryParameter("date", date).
                addQueryParameter("host", url.getHost()).
                build();
        return httpUrl.toString();
    }

    @Slf4j
    private static class BlockingQueuePool {
        private static final int SIZE = 20;
        private static final LinkedBlockingQueue<LinkedBlockingQueue<String>> POOL = new LinkedBlockingQueue<>(SIZE);

        static {
            for (int i = 0; i < SIZE; i++) {
                try {
                    POOL.put(new LinkedBlockingQueue<>(1));
                } catch (InterruptedException e) {
                    log.error("e", e);
                }
            }
        }

        public static LinkedBlockingQueue<String> get() {
            return POOL.poll();
        }

        public static void put(LinkedBlockingQueue<String> queue) {
            try {
                POOL.put(queue);
            } catch (InterruptedException e) {
                log.error("e", e);
            }
        }
    }
}
