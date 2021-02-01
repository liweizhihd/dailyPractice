package com.wz.poc.audio2text.model;

import java.util.Arrays;

/**
 * @author liweizhi
 * @date 2021/1/6
 */
public class Decoder {
    private TextWrapper[] texts;
    private int defc = 10;

    public Decoder() {
        this.texts = new TextWrapper[this.defc];
    }

    public synchronized void decode(TextWrapper text) {
        if (text.sn >= this.defc) {
            this.resize();
        }
        if ("rpl".equals(text.pgs)) {
            for (int i = text.rg[0]; i <= text.rg[1]; i++) {
                this.texts[i].deleted = true;
            }
        }
        this.texts[text.sn] = text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextWrapper t : this.texts) {
            if (t != null && !t.deleted) {
                sb.append(t.text);
            }
        }
        return sb.toString();
    }

    public void resize() {
        int oc = this.defc;
        this.defc <<= 1;
        TextWrapper[] old = this.texts;
        this.texts = new TextWrapper[this.defc];
        for (int i = 0; i < oc; i++) {
            this.texts[i] = old[i];
        }
    }

    public void discard() {
        Arrays.fill(this.texts, null);
    }
}
