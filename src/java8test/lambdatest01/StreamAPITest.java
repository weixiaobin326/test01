package java8test.lambdatest01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author weixiaobin
 * @Date 2019/1/17 11:20
 */
public class StreamAPITest {
    List<Employee> employeeList = Arrays.asList(
            new Employee("qq",29,9000),
            new Employee("ww",27,4000),
            new Employee("ww",27,4000),
            new Employee("ww",27,4000),
            new Employee("ee",20,2000),
            new Employee("tt",26,1000)
    );

    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

    @Test
    public void test01() {
        employeeList.stream()
                    .filter((x) -> x.getAge() > 25)
                    .limit(2)
                    .skip(1)
                    .distinct()
                    .forEach(System.out::println);
    }
}
