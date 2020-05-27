package com.fuchangling.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author:wangzhen
 * @version:V1.0 2018年8月22日
 */
public class DateUtil {

    /**
     * 默认时间格式
     */
    public static String simpleDateFormat = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年月日格式
     */
    public static String yearMonthDayFormat = "yyyy-MM-dd";
    /**
     * 年月格式
     */
    public static String yearMonthFormat = "yyyy-MM";
    /**
     * 年格式
     */
    public static String yearFormat = "yyyy";

    /**
     * 获取当前时间字符串（默认时间格式）
     * 2018年8月22日
     *
     * @return author:wangzhen
     */
    public static String getNowDateStr() {
        return DateFormatUtils.format(new Date(), simpleDateFormat);
    }

    /**
     * 获取当前时间字符串（根据传入的格式）
     * 2018年8月22日
     *
     * @param pattern
     * @return author:wangzhen
     */
    public static String getNowDateStr(String pattern) {
        if (pattern == null) {
            pattern = simpleDateFormat;
        }
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 获取传入日期的字符串（默认时间格式）
     * 2018年8月22日
     *
     * @param date
     * @return author:wangzhen
     */
    public static String getDateStr(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, simpleDateFormat);
    }

    /**
     * 获取传入日期的字符串（默认时间格式）
     * 2018年8月22日
     *
     * @param date
     * @return author:wangzhen
     */
    public static String getDateStr(Timestamp date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, simpleDateFormat);
    }

    /**
     * 获取传入日期的字符串（根据传入格式）
     * 2018年8月22日
     *
     * @param date
     * @param pattern
     * @return author:wangzhen
     */
    public static String getDateStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 将传入格式字符串按照默认格式转为日期
     * 2018年8月22日
     *
     * @param dateStr
     * @return author:wangzhen
     */
    public static Date getDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将传入格式字符串按照指定格式转为日期
     * 2018年8月22日
     *
     * @param pattern
     * @return author:wangzhen
     */
    public static Date getDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 该方法是根据传入的一个日期对象和日期格式，返回符合日期格式的字符串.日期格式中的
     * 字符有:yyyy表示四位年，MM表示二位月，dd表示二位日，HH表示二位时，mm表示二位分，
     * ss表示二位秒.用户可以根据需要使用这些字符组合.
     *
     * @param d
     * @param fmstr
     * @return String
     * @roseuid 4367277A01AB
     */
    public static String fmtDate(Date d, String fmstr) {
        try {
            Calendar cur = Calendar.getInstance();
            cur.setTime(d);
            String YYYY = (new Integer(cur.get(Calendar.YEAR))).toString();
            String YY = YYYY.substring(2, 4);

            String MM = "";
            int m = cur.get(Calendar.MONTH) + 1;
            if (m < 10) {
                MM = "0" + m;
            } else {
                MM = "" + m;
            }

            String DD = "";
            int td = cur.get(Calendar.DATE);
            if (td >= 10) {
                DD = "" + td;
            } else {
                DD = "0" + td;
            }

            String hh = "";
            int ih = cur.get(Calendar.HOUR_OF_DAY);
            if (ih >= 10) {
                hh = "" + ih;
            } else {
                hh = "0" + ih;
            }

            String mm = "";
            int im = cur.get(Calendar.MINUTE);
            if (im >= 10) {
                mm = "" + im;
            } else {
                mm = "0" + im;
            }

            String ss = "";
            int is = cur.get(Calendar.SECOND);
            if (is >= 10) {
                ss = "" + is;
            } else {
                ss = "0" + is;
            }

            String str = fmstr;
            int y = str.indexOf("yyyy");
            int len = str.length();
            if (y != -1) {
                str = str.substring(0, y) + YYYY + str.substring(y + 4, len);
            } else {
                y = str.indexOf("yy");
                if (y != -1) {
                    str = str.substring(0, y) + YY + str.substring(y + 2, len);
                }
            }

            int M = str.indexOf("MM");
            if (M != -1) {
                str = str.substring(0, M) + MM + str.substring(M + 2, len);
            }

            int r = str.indexOf("dd");
            if (r != -1) {
                str = str.substring(0, r) + DD + str.substring(r + 2, len);
            }

            int h = str.indexOf("HH");
            if (h != -1) {
                str = str.substring(0, h) + hh + str.substring(h + 2, len);
            }

            int sm = str.indexOf("mm");
            if (sm != -1) {
                str = str.substring(0, sm) + mm + str.substring(sm + 2, len);
            }

            int s = str.indexOf("ss");
            if (s != -1) {
                str = str.substring(0, s) + ss + str.substring(s + 2, len);
            }

            return str;
        } catch (Exception e) {

            return "";
        }
    }

}
