package com.letter.otools;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Anniversary extends LitePalSupport implements Serializable{

    public static final int ANNI_TYPE_ONLY_ONCE = 0;
    public static final int ANNI_TYPE_EVERY_YEAR = 1;
    public static final int ANNI_TYPE_COUNT_DOWN = 2;

    private static final  long MS_ONE_DAY = 86400000L;

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
        this.time = (new Date().getTime() / MS_ONE_DAY) * MS_ONE_DAY;
        this.text = "";
        this.type = ANNI_TYPE_ONLY_ONCE;
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

    public String getDaysText() {
        String text = new String();
        Date now = new Date();
        switch (type) {
            case ANNI_TYPE_ONLY_ONCE:
                if (time <= now.getTime()) {
                    text = String.valueOf((now.getTime() - time) / MS_ONE_DAY) + "天";
                } else {
                    text = "差" + String.valueOf((time - now.getTime()) / MS_ONE_DAY) + "天";
                }
                break;

            case ANNI_TYPE_EVERY_YEAR:
                if (time <= now.getTime()) {
                    text = String.valueOf((now.getTime() - time) / MS_ONE_DAY) + "天";
                } else {
                    text = "差" + String.valueOf((time - now.getTime()) / MS_ONE_DAY) + "天";
                }
                break;

            case ANNI_TYPE_COUNT_DOWN:
                if (time <= now.getTime()) {
                    text = "已过" + String.valueOf((now.getTime() - time) / MS_ONE_DAY) + "天";
                } else {
                    text = "余" + String.valueOf((time - now.getTime()) / MS_ONE_DAY) + "天";
                }
                break;

            default:
                break;
        }
        return text;
    }

    public String getTypeText () {
        String text = new String();
        Date now = new Date();
        switch (type) {
            case ANNI_TYPE_ONLY_ONCE:
                text = typeText[type];
                break;

            case ANNI_TYPE_EVERY_YEAR:
                if (time <= now.getTime()) {
                    int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(now.getTime()));

                    int month = Integer.parseInt(new SimpleDateFormat("MM").format(time));
                    int day = Integer.parseInt(new SimpleDateFormat("dd").format(time));

                    Date tmpDate = new Date();
                    try {
                        tmpDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(nowYear)
                                        + "-" + month + "-" + day);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if ((tmpDate.getTime() - now.getTime()) / MS_ONE_DAY > 0) {
                        text = typeText[type] + " · " + String.valueOf((tmpDate.getTime() - now.getTime()) / MS_ONE_DAY) + "天";
                    } else if ((tmpDate.getTime() - now.getTime()) / MS_ONE_DAY == 0) {
                        text = typeText[type] + " · 今天";
                    } else {
                        try {
                            tmpDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(nowYear + 1)
                                    + "-" + month + "-" + day);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        text = typeText[type] + " · " + String.valueOf((tmpDate.getTime() - now.getTime()) / MS_ONE_DAY) + "天";
                    }
                } else {
                    text = typeText[type];
                }
                break;

            case ANNI_TYPE_COUNT_DOWN:
                text = typeText[type];
                break;

            default:
                break;
        }
        return text;
    }
}
