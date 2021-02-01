package test.java8.date;

import org.springframework.util.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/31 16:28
 * @Description:
 */
public class DateUtil {

    public static final long ONE_DAY = 1000 * 3600 * 24;

    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter MONTH_DAY = DateTimeFormatter.ofPattern("MM-dd");

    public static String getDate4Chart() {
        return MONTH_DAY.format(LocalDateTime.now());
    }

    /**
     * @author liweizhi
     * Date 2019/4/18 下午3:36
     * Description 获取某天前的开始和结束的时间戳，毫秒级
     */
    public static long[] getOneDayPeriodStamp(int someDaysAgo) {
        long[] ret = new long[3];
        long todayBegin = LocalDate.now().toEpochDay() * ONE_DAY - TimeZone.getDefault().getRawOffset();
        long targetBegin = todayBegin - ONE_DAY * someDaysAgo;
        long targetEnd = targetBegin + ONE_DAY - 1;
        ret[0] = targetBegin;
        ret[1] = targetEnd;
        return ret;
    }

    /**
     * @author liweizhi
     * Date 2019/4/18 下午3:36
     * Description 获取某天前的开始和结束的时间字符串，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String[] getOneDayPeriodDateTime(int someDaysAgo) {
        String[] ret = new String[3];
        long todayBegin = LocalDate.now().toEpochDay() * ONE_DAY - TimeZone.getDefault().getRawOffset();
        long targetBegin = todayBegin - ONE_DAY * someDaysAgo;
        long targetEnd = targetBegin + ONE_DAY - 1;
        ret[0] = convertTimeToString(targetBegin);
        ret[1] = convertTimeToString(targetEnd);
        ret[2] = convertTimeToString2(targetBegin);
        return ret;
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：dtf(yyyy-MM-dd HH:mm:ss)
     */
    public static String convertTimeToString(Long time) {
        Assert.notNull(time, "time is null");
        return dtf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：MONTH_DAY(MM-dd)
     */
    public static String convertTimeToString2(Long time) {
        Assert.notNull(time, "time is null");
        return MONTH_DAY.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Long convertTimeToLong(String time) {
        Assert.notNull(time, "time is null");
        LocalDateTime parse = LocalDateTime.parse(time, dtf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 取本月第一天
     */
    public static LocalDate firstDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 取本月第N天
     */
    public static LocalDate dayOfThisMonth(int n) {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(n);
    }

    /**
     * 取本月最后一天
     */
    public static LocalDate lastDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 取本月第一天的开始时间
     */
    public static LocalDateTime startOfThisMonth() {
        return LocalDateTime.of(firstDayOfThisMonth(), LocalTime.MIN);
    }

    /**
     * 取本月最后一天的结束时间
     */
    public static LocalDateTime endOfThisMonth() {
        return LocalDateTime.of(lastDayOfThisMonth(), LocalTime.MAX);
    }

    public static void main(String[] args) {
        System.out.println(getDate4Chart());
    }
}
