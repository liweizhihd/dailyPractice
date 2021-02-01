package others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: liweizhi
 * Date: 2019/3/22 16:32
 * Description:
 */
public class Test {

    public static void main(String args[]) {
        String str = "dd/MMM/yyyy:HH:mm:ss Z";
        String date = "22/Apr/2019:10:06:51 +0800";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        try {
            System.out.println(sdf.format(new Date()));
            //System.out.println(sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test001() {
        synchronized (this) {
            System.out.println("这是成员方法的synchronized (this)代码块");
        }
    }

    public synchronized void test002() {
        System.out.println("这是这是成员方法的synchronized成员方法");
    }
}
