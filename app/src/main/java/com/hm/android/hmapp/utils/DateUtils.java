package com.hm.android.hmapp.utils;

import android.util.Log;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    protected static String TAG = DateUtils.class.getName();

    //标准时间
    public final static String TIME_FORMAT   = "yyyy-MM-dd HH:mm:ss";
    //标准时间01
    public static final String DATE_FORMAT   = "yyyy-MM-dd";


    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;

    /******************** 时间相关常量 ********************/
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;


    /**
     * @throws
     * @方法描述：格式化获取的时间
     * @方法名：formatDate yyyy-MM-dd HH:mm:ss
     * @参数：@param currentTime
     * @参数：@return
     * @返回：String
     */
    public static String formatDate(Long currentTime) {
        DateFormat format = null;
        try {
            format = new SimpleDateFormat(TIME_FORMAT);
            Date date = new Date(currentTime);
            return format.format(date);
        } catch (Exception e) {
            //发现异常时，返回当前时间
            Log.e(TAG , e.getMessage());
            return format.format(new Date());
        }
    }

    /**
     * 秒数 转为 天小时分秒格式
     *
     * @throws
     * @创建人：jinxiangdong
     * @修改时间：2015年6月18日 下午3:05:24
     * @方法描述：
     * @方法名：toTime
     * @参数：@param totalSecond
     * @参数：@return
     * @返回：String
     */
    public static String toTime(int totalSecond) {
        String timeStr = "";
        int days = totalSecond / 60 / 60 / 24;
        int remain = totalSecond % (60 * 60 * 24);
        int hours = remain / (60 * 60);
        remain = remain % (60 * 60);
        int minute = remain / (60);
        int second = remain % 60;
        if (days > 0) {
            timeStr = days + "天";
        }
        if (hours > 0) {
            timeStr += hours + "小时";
        }
        if (minute > 0) {
            timeStr += minute + "分";
        }
        if (second > 0) {
            timeStr += second + "秒";
        }
        return timeStr;
    }

    public static String formatDate(Long currentTime, String fromat) {
        DateFormat format = null;
        try {
            format = new SimpleDateFormat(fromat);
            Date date = new Date(currentTime);
            return format.format(date);
        } catch (Exception e) {
            //发现异常时，返回当前时间
            Log.e(TAG , e.getMessage());
            return format.format(new Date());
        }
    }

    /**
     * 判断日期是否是今天
     *
     * @throws
     * @创建人：jinxiangdong
     * @修改时间：2015年6月13日 下午4:22:37
     * @方法描述：
     * @方法名：isToday
     * @参数：@param currentTime
     * @参数：@return
     * @返回：Boolean
     */
    public static Boolean isToday(Long currentTime) {
        boolean b = false;
        String currentDateStr = formatDate(currentTime, DATE_FORMAT);
        Long today = System.currentTimeMillis();

        String nowDateStr = formatDate(today, DATE_FORMAT);

        if (nowDateStr.equals(currentDateStr)) {
            b = true;
        }
        return b;
    }

    /**
     * @throws
     * @方法描述：
     * @方法名：toDate
     * @参数：@param str
     * @参数：@return
     * @返回：long
     */
    public static long toDate(String str, String formatStr) {
        DateFormat format = null;

        try {
            format = new SimpleDateFormat(formatStr);
            return format.parse(str).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return 0L;
        }
    }

    public enum TimeUnit {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *                     <li>{@link TimeUnit#SEC }: 秒</li>
     *                     <li>{@link TimeUnit#MIN }: 分</li>
     *                     <li>{@link TimeUnit#HOUR}: 小时</li>
     *                     <li>{@link TimeUnit#DAY }: 天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / MSEC;
            case SEC:
                return milliseconds / SEC;
            case MIN:
                return milliseconds / MIN;
            case HOUR:
                return milliseconds / HOUR;
            case DAY:
                return milliseconds / DAY;
        }
        return -1;
    }



    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link TimeUnit#SEC }: 秒</li>
     *              <li>{@link TimeUnit#MIN }: 分</li>
     *              <li>{@link TimeUnit#HOUR}: 小时</li>
     *              <li>{@link TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, new SimpleDateFormat( DATE_FORMAT) );
    }
    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link TimeUnit#SEC }: 秒</li>
     *               <li>{@link TimeUnit#MIN }: 分</li>
     *               <li>{@link TimeUnit#HOUR}: 小时</li>
     *               <li>{@link TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format) - string2Milliseconds(time1, format), unit));
    }


    public static long getIntervalTime(long time0 , long time1 , TimeUnit unit ){
        return Math.abs( milliseconds2Unit( time0 - time1 , unit ) );
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time,  new SimpleDateFormat( DATE_FORMAT) );
    }
    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
