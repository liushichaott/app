package com.example.liushichao.myapplication.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转化类
 */
public class DateFormatUtils {
    private static final String DETAIL_TIME = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat format_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat format_DATE_NO_YEAR = new SimpleDateFormat("MM-dd HH:mm");
    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 获取当前的日期 （YYYY-MM-DD）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateYYYYMMDD(long currentTimeMillis) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentTimeMillis));
    }

    /**
     * 获取当前的时间 （HH:mm:ss）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateHHmmss(long currentTimeMillis) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(currentTimeMillis));
    }

    /**
     * 获取当前的时间 （HH:mm）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateHHmm(long currentTimeMillis) {
        return new SimpleDateFormat("HH:mm").format(new Date(currentTimeMillis));
    }

    /**
     * 获取当前的日期 （YYYY-MM-DD）
     *
     * @param currentTimeMillis
     * @return
     */
    public static ArrayList<String> getDateYYYYMM(long currentTimeMillis) {
        ArrayList<String> dates = new ArrayList<>();
        dates.add(new SimpleDateFormat("yyyy").format(new Date(currentTimeMillis)));
        dates.add(new SimpleDateFormat("MM").format(new Date(currentTimeMillis)));
        dates.add(new SimpleDateFormat("dd").format(new Date(currentTimeMillis)));
        return dates;
    }

    /**
     * 获取当前的日期 （yyyy-MM-dd HH:mm:ss）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateYYYYMMDDHHmmss(long currentTimeMillis) {
        return new SimpleDateFormat(DETAIL_TIME).format(new Date(currentTimeMillis));
    }

    /**
     * 获取当前的日期 （MM-DD）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateMMDD(long currentTimeMillis) {
        return new SimpleDateFormat("MM-dd").format(new Date(currentTimeMillis));
    }

    /**
     * 获取当前的日期 （YYYY-MM-DD hh:mm）
     *
     * @param currentTimeMillis
     * @return
     */
    public static String getDateYYYYMMDDhhmm(long currentTimeMillis) {
        return format_DATE.format(new Date(currentTimeMillis));
    }

    /**
     * 得到日期时间戳
     *
     * @param dateTime
     * @return
     */
    public static long getDateTimeMillis(String dateTime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到指定月份日期(MM-dd)的时间戳
     *
     * @param dateTime
     * @return
     */
    public static long getMMddMillis(String dateTime) {
        try {
            return new SimpleDateFormat("MM-dd").parse(dateTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss 转换成时间戳
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeMillis2(String dateTime) {
        if (TextUtils.isEmpty(dateTime)) {
            return parseTimestampToString(0);
        }
        long time;
        try {
            time = new SimpleDateFormat(DETAIL_TIME).parse(dateTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            time = 0;
        }
        return parseTimestampToString(time);
    }


    /**
     * 将时间戳转换为 模糊时间 其实一般工具类里对异常不做处理，交给调用者处理
     * 需要转换的时间戳
     */
    public static String parseTimestampToString(long create_time) {
        long during = create_time;
        long nowL = System.currentTimeMillis();// 当前系统毫秒数
//        LogUtil.d("当前系统时间戳 毫秒数==" + nowL);
//        LogUtil.d("返回的时间戳秒数==" + create_time);
        if (during == 0) {
            return "不久前";
        }
        during = nowL - during;// 算出距离现在间隔时间 毫秒
//        LogUtil.d("距离当前时间毫秒数==" + during);
        during = during / 1000 / 60;// 算出距离现在间隔时间 分钟
//        LogUtil.d("距离现在间隔时间 分钟数==" + during);
        if (during <= 0) {// 一分钟前
            return "刚刚";
        } else if (during < 60) {// 由于系统时间差异
            return during + "分钟前";
        } else {
            during = during / 60;// 小时
            if (during < 24) {
                return during + "小时前";
            } else {
//                if (during < 30) {
//                    return format_DATE_NO_YEAR.format(new Date(create_time));
//                } else {
                during = during / 24 / 30;// 月
                if (during < 12) {
                    return format_DATE_NO_YEAR.format(new Date(create_time));
                } else {
                    return format_DATE.format(new Date(create_time));
                }
//                }
            }
        }
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 根据指定格式将指定字符串解析成日期
     *
     * @param str     指定日期
     * @param pattern 指定格式
     * @return 返回解析后的日期
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期是星期几
     *
     * @param date 指定日期
     * @return 返回星期几的描述
     */
    public static String getWeekdayDesc(Date date) {
        final String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四",
                "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是今年
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今年 false不是
     * @throws ParseException
     */
    public static boolean IsYesYear(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            return true;
        }
        return false;
    }

    public static String getJudgeYear(long createTime) {
        try {
            if (IsYesYear(getDateYYYYMMDD(createTime))) {
                return getDateMMDD(createTime);
            } else {
                return getDateYYYYMMDD(createTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }
}
