package java8test.lambdatest01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author weixiaobin
 * @Date 2019/1/18 11:08
 */
public class StreamTest03 {
    @Test
    public void test01() {
        Integer[] integers = {1, 2, 3, 4, 5};

        Arrays.stream(integers)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }


}
