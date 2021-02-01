package com.wz.poc.audio2text.model;

import com.google.gson.JsonObject;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public class Result {
    int bg;
    int ed;
    String pgs;
    int[] rg;
    int sn;
    Ws[] ws;
    boolean ls;
    JsonObject vad;

    public TextWrapper getText() {
        TextWrapper text = new TextWrapper();
        StringBuilder sb = new StringBuilder();
        for (Ws wsItem : this.ws) {
            sb.append(wsItem.cw[0].w);
        }
        text.sn = this.sn;
        text.text = sb.toString();
        text.rg = this.rg;
        text.pgs = this.pgs;
        text.bg = this.bg;
        text.ed = this.ed;
        text.ls = this.ls;
        text.vad = this.vad;
        return text;
    }
}
