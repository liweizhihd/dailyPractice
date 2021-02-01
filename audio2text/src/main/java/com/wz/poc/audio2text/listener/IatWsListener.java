package com.wz.poc.audio2text.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wz.poc.audio2text.constant.Audio2textConstant;
import com.wz.poc.audio2text.constant.Audio2textParamConfig;
import com.wz.poc.audio2text.constant.AudioUploadEnum;
import com.wz.poc.audio2text.model.Decoder;
import com.wz.poc.audio2text.model.ResponseData;
import com.wz.poc.audio2text.model.TextWrapper;
import com.wz.poc.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.LinkedBlockingQueue;

import static com.wz.poc.audio2text.constant.Audio2textConstant.*;

/**
 * 讯飞语音听写WebApi文档: https://www.xfyun.cn/doc/asr/voicedictation/API.html
 *
 * @author liweizhi
 * @date 2021/1/6
 */
@Slf4j
public class IatWsListener extends WebSocketListener {
    public static final Gson GSON = new Gson();
    Decoder decoder = new Decoder();

    private Audio2textParamConfig config;
    private String audioUrl;
    LinkedBlockingQueue<String> blockingDeque;

    public IatWsListener(Audio2textParamConfig config, String audioUrl, LinkedBlockingQueue<String> blockingDeque) {
        this.config = config;
        this.audioUrl = audioUrl;
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        AudioUploadEnum uploadParamEnum = AudioUploadEnum.MP3_16K;
        // 链接(握手)成功,上传音频
        int status = Audio2textConstant.STATUS_FIRST_FRAME;
        // 从阿里云读取音频对象
        try (Response audioResp = OkHttpUtil.getResponseNotClose(audioUrl)) {
            InputStream fs = audioResp.body().byteStream();
            byte[] buffer = new byte[uploadParamEnum.getFrameSize()];
            // 发送音频
            while (true) {
                int len = fs.read(buffer);
                if (len == -1) {
                    status = Audio2textConstant.STATUS_LAST_FRAME;  //文件读完，改变status 为 2
                }

                if (status == Audio2textConstant.STATUS_FIRST_FRAME) {
                    JsonObject frame = new JsonObject();
                    JsonObject business = new JsonObject();  //第一帧 必须发送 first
                    JsonObject common = new JsonObject();  //第一帧 必须发送 first
                    JsonObject data = new JsonObject();  //每一帧 都要发送 every
                    // 填充common
                    common.addProperty("app_id", config.getAppid());
                    //填充business
                    business.addProperty("language", "zh_cn");
                    // 应用领域
                    business.addProperty("domain", "iat");
                    // 方言
                    business.addProperty("accent", "mandarin");
                    // 静默多长时间后引擎认为音频结束,单位是毫秒
                    business.addProperty("vad_eos", 3000);

                    //填充data
                    data.addProperty(FIELD_STATUS, Audio2textConstant.STATUS_FIRST_FRAME);
                    data.addProperty(FIELD_FORMAT, uploadParamEnum.getFormat());
                    data.addProperty(FIELD_ENCODING, uploadParamEnum.getEncoding());
                    data.addProperty(FIELD_AUDIO, Base64.getEncoder().encodeToString(Arrays.copyOf(buffer, len)));
                    //填充frame
                    frame.add("common", common);
                    frame.add("business", business);
                    frame.add("data", data);
                    webSocket.send(frame.toString());
                    status = Audio2textConstant.STATUS_CONTINUE_FRAME;  // 发送完第一帧改变status 为 1
                    log.info("send start");
                } else if (status == Audio2textConstant.STATUS_CONTINUE_FRAME) {
                    JsonObject frame1 = new JsonObject();
                    JsonObject data1 = new JsonObject();
                    data1.addProperty(FIELD_STATUS, Audio2textConstant.STATUS_CONTINUE_FRAME);
                    data1.addProperty(FIELD_FORMAT, uploadParamEnum.getFormat());
                    data1.addProperty(FIELD_ENCODING, uploadParamEnum.getEncoding());
                    data1.addProperty(FIELD_AUDIO, Base64.getEncoder().encodeToString(Arrays.copyOf(buffer, len)));
                    frame1.add("data", data1);
                    webSocket.send(frame1.toString());
                } else {
                    JsonObject frame2 = new JsonObject();
                    JsonObject data2 = new JsonObject();
                    data2.addProperty(FIELD_STATUS, Audio2textConstant.STATUS_LAST_FRAME);
                    data2.addProperty(FIELD_AUDIO, "");
                    data2.addProperty(FIELD_FORMAT, uploadParamEnum.getFormat());
                    data2.addProperty(FIELD_ENCODING, uploadParamEnum.getEncoding());
                    frame2.add("data", data2);
                    webSocket.send(frame2.toString());
                    log.info("sendlast");
                    break;
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);

        ResponseData resp = GSON.fromJson(text, ResponseData.class);
        if (resp != null) {
            if (resp.getCode() != 0) {
                log.error("code=>{} error=>{} sid={}", resp.getCode(), resp.getMessage(), resp.getSid());
                log.error("错误码查询链接：https://www.xfyun.cn/document/error-code");
                return;
            }
            if (resp.getData() != null) {
                String textToString = "";
                if (resp.getData().getResult() != null) {
                    TextWrapper te = resp.getData().getResult().getText();
                    try {
                        decoder.decode(te);
                        textToString = decoder.toString();
                        log.info("中间识别结果 ==》" + textToString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (resp.getData().getStatus() == 2) {
                    // resp.data.status ==2 说明数据全部返回完毕，可以关闭连接，释放资源
                    blockingDeque.offer(textToString);
                    log.info("session end ");
                    log.info("最终识别结果 ==》" + textToString);
                    log.info("本次识别sid ==》" + resp.getSid());
                    decoder.discard();
                    webSocket.close(1000, "");
                }
            }
        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);

        if (null != response) {
            int code = response.code();
            log.error("onFailure code:{}\nonFailure body:{}", code, response.body());
            if (101 != code) {
                log.error("connection failed");
            }
        }
    }
}
