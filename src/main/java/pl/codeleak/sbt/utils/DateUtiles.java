package pl.codeleak.sbt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/6.
 */
public class DateUtiles {
    /**
     * 日期处理
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null)
            return "-";
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    /**
     * 日期处理
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null)
            return "-";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(date);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = 0;
        if (date1 != null && date2 != null)
            days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)) + 1;
        return days;
    }
}
