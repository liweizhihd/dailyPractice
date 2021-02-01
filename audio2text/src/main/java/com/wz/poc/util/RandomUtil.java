package com.wz.poc.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liweizhi
 * @date 2020/12/30
 */
@Slf4j
public class RandomUtil {
    private static Random random = new Random();

    public static int random(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        int times = 10000;
        int min = 1;
        int max = 10;
        Map<Integer, Integer> map = new HashMap<>(times);
        int tmp;
        Integer oldTime;
        for (int i = 0; i < times; i++) {
            tmp = random(min, max);
            oldTime = map.computeIfAbsent(tmp, integer -> 0);
            oldTime++;
            map.put(tmp, oldTime);
        }

        log.info("map:{}", map);
    }
}
