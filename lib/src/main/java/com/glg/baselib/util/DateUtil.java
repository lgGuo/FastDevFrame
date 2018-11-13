package com.glg.baselib.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 年月日模式
     */
    public static final String YMD_PATTERN_1 = "yyyy-MM-dd";//2017-02-02
    public static final String YMD_PATTERN_2 = "yyyy/MM/dd";//2017/02/02
    public static final String YMD_PATTERN_3 = "yyyy年MM月dd日";//2017年02月02日
    public static final String YMD_PATTERN_4 = "yyyy年M月d日";//2017年2月2日 省去个位数时前面的0格式
    public static final String YMD_PATTERN_5 = "yyyy.MM.dd";//2017.02.02

    public static final String YMD_PATTERN_6 = "MM月dd";//02.02
    public static final String YMD_PATTERN_7 = "yyyy年MM月";//02.02


    /**
     * 时分秒模式
     */
    public static final String HMS_PATTERN_1 = "HH:mm:ss";//13:30:15
    public static final String HMS_PATTERN_2 = "HH-mm-ss";//13-30-15
    public static final String HMS_PATTERN_3 = "HH时mm分ss秒";//13时30分15秒
    public static final String HMS_PATTERN_4 = "hh:mm:ss";//01:30:15  采用12小时制格式
    public static final String HMS_PATTERN_6 = "HH:mm";//01:30:15  采用12小时制格式
    public static final String HMS_PATTERN_5 = "h:m:ss";//1:2:15  采用12小时制格式 省去个位数时前面的0格式
    public static final String HMS_PATTERN_7 = "HH";//1:2:15  采用12小时制格式 省去个位数时前面的0格式

    /**
     * 星期模式
     */
    public static final String WEEK_PATTERN_1 = "EEEE";//星期一
    public static final String WEEK_PATTERN_2 = "EE";//周一


    /**
     * 把已知时间格式换成目标格式
     *
     * @param timestamp 毫秒
     * @param newPattern 模式
     */
    public static String formateTimeStamp(long timestamp, String newPattern) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(newPattern);
        return simpleDateFormat.format(new Date(timestamp));

    }






}
