package com.wz.poc.audio2text.model;

import com.google.gson.JsonObject;

import java.util.Arrays;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public class TextWrapper {
    int sn;
    int bg;
    int ed;
    String text;
    String pgs;
    int[] rg;
    boolean deleted;
    boolean ls;
    JsonObject vad;

    @Override
    public String toString() {
        return "Text{" +
                "bg=" + bg +
                ", ed=" + ed +
                ", ls=" + ls +
                ", sn=" + sn +
                ", text='" + text + '\'' +
                ", pgs=" + pgs +
                ", rg=" + Arrays.toString(rg) +
                ", deleted=" + deleted +
                ", vad=" + (vad == null ? "null" : vad.getAsJsonArray("ws").toString()) +
                '}';
    }
}
