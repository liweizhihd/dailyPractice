package com.wz.poc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liweizhi
 * @date 2021/1/8
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    private static ObjectMapper om = new ObjectMapper();

    public static <T> T parse(String str, Class<T> clazz) {
        try {
            return om.readValue(str, clazz);
        } catch (Exception e) {
            log.error("jackson read error", e);
            return null;
        }
    }

    /**
     * 把对象转换成Json格式字符串
     *
     * @param obj 对象
     * @return son格式字符串
     */
    public static String toJsonString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("jackson write error", e);
            return null;
        }
    }
}
