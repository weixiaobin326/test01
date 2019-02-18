package java8test.data;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author weixiaobin
 * @Date 2019/1/21 10:47
 */
public class DataTest01 {
    @Test
    public void test01() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

    }
    @Test
    public void test02() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年mm月dd天 hh时MM分ss秒");
        LocalDateTime localDateTime = LocalDateTime.now();

        String format = dateTimeFormatter.format(localDateTime);

        System.out.println(format);
    }
}
