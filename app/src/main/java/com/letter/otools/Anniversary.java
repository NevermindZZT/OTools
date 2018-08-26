package com.letter.otools;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Anniversary extends LitePalSupport {

    public static final int ANNI_TYPE_ONLY_ONCE = 0;
    public static final int ANNI_TYPE_EVERY_YEAR = 1;
    public static final int ANNI_TYPE_COUNT_DOWN = 2;

    public static final String[] typeText = {
            "纪念日",
            "周年纪念",
            "倒计时",
    };

    private int id;
    private long time;
    private String text;
    private int type;

    public Anniversary() {
        this.time = new Date().getTime();
        this.text = "";
        this.type = ANNI_TYPE_ONLY_ONCE;
    }

    public Anniversary(long time, String text) {
        this.time = time;
        this.text = text;
        this.type = ANNI_TYPE_ONLY_ONCE;
    }

    public Anniversary (long time, String text, int type) {
        this.time = time;
        this.text = text;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
