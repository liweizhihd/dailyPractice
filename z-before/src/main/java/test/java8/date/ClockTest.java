package test.java8.date;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 17:45
 * Description:
 */
public class ClockTest {

    @Test
    public void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println("clock =" + clock);
        long millis = clock.millis();
        System.out.println(millis);//1552379579043
        System.out.println(System.currentTimeMillis());
        Instant instant = clock.instant();
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());
        Date legacyDate = Date.from(instant); //2019-03-12T08:46:42.588Z
        System.out.println(legacyDate);//Tue Mar 12 16:32:59 CST 2019
    }

    @Test
    public void timeZones() {
        //输出所有区域标识符
        ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());// ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone2.getRules());// ZoneRules[currentStandardOffset=-03:00]
    }
}
