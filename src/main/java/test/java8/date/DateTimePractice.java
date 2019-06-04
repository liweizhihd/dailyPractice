package test.java8.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * https://www.cnblogs.com/comeboo/p/5378922.html
 * @Auther: liweizhi
 * @Date: 2018/12/18 09:38
 * @Description:
 */

@Slf4j
public class DateTimePractice {

    private final static ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HH:mm:ss"));
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");

    public static void main(String[] args) {
        System.out.println(dtf.format(LocalDate.now()));
    }

    @Test
    public void format(){
        //System.out.println(dtf.format(LocalDate.now()));

        //YearMonth parse = YearMonth.parse("201811", dtf);
       // System.out.println(parse);

//        Instant ins = Instant.now();
//        System.out.println(ins);
//        System.out.println(ins.toEpochMilli());
//        Instant ins2 = Instant.now(Clock.systemDefaultZone());
//        System.out.println(ins2);
//        System.out.println(ins2.toEpochMilli() + "\n========================");

/*        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(instant);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));
        System.out.println(localDateTime);*/

        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
    }

    @Test
    public void today() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期是："+today);
        System.out.println("明天的日期是：" + today.plusDays(1));
        System.out.println("10个月后的日期是：" + today.plus(10, ChronoUnit.MONTHS));

        System.out.println(today.getYear());
        //System.out.println(today.getMonth());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());
    }

    @Test
    public void getOneDate(){
        LocalDate someDay = LocalDate.of(2018,8,31);
        System.out.println(someDay);
    }

    @Test
    public void compareDate(){
        LocalDate someDay = LocalDate.of(2018,8,31);
        LocalDate someDay2 = LocalDate.now();
        System.out.println(someDay.isEqual(someDay2));
        System.out.println(someDay.isBefore(someDay2));
        System.out.println(someDay.isAfter(someDay2));
    }

    @Test
    public void isBirthday(){
        LocalDate birthDate = LocalDate.of(1993,10,10);
        //LocalDate nowDate = LocalDate.now();
        MonthDay birthday = MonthDay.from(birthDate);
        MonthDay today = MonthDay.now(ZoneId.systemDefault());
        System.out.println("birthday:" + birthday + ",today:" + today);
        String ret = birthday.equals(today) ? "今天生日" : "不是生日";
        System.out.println(ret);

    }

    @Test
    public void clock(){
        System.out.println(Clock.systemUTC());
        System.out.println(Clock.systemDefaultZone());

    }

    @Test
    public void zone(){
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone = ZoneId.of(ZoneId.SHORT_IDS.get("ACT"));
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(LocalDateTime.now(zone));
        System.out.println(LocalDateTime.now(defaultZone));
    }

    @Test
    public void dateWithZone(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        System.out.println(LocalDateTime.now(ZoneId.of("GMT")));
    }

    @Test
    public void betweenDate(){
        LocalDate date1 = LocalDate.of(2017,7,3);
        LocalDate date2 = LocalDate.of(2018,6,2);
        Period between = Period.between(date1, date2);
        System.out.println(between.getDays());
        System.out.println(between.getMonths());
        System.out.println(between.getYears());
    }

    @Test
    public void beetweenDays(){
        LocalDate dateStart = LocalDate.of(2018, 8, 31);
        LocalDate dateEnd = LocalDate.now();
        log.info("想离职的第{}天。", dateEnd.toEpochDay() - dateStart.toEpochDay());
    }


}
