package com.wz.poc.temp;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author liweizhi
 * @date 2021/1/18
 */
@Slf4j
public class ReallyTemp {
    @Test
    void tmpTest() {
        Map<String, Object> map = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("s");
        set.add("d");

        map.put("set", set);
        map.put("hi", "jack");

        Set<String> setGeted = new HashSet<>((Set<String>) map.get("set"));
        setGeted.remove("s");

        log.info("map:{}", map);
        log.info("set:{}", set);
        log.info("setGeted:{}", setGeted);
    }
}
