package java8test.lambdatest01;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author weixiaobin
 * @Date 2019/1/16 15:55
 */
public class LambdaTest2 {
    List<Employee> employeeList = Arrays.asList(
            new Employee("qq",29,9000),
            new Employee("ww",27,4000),
            new Employee("ee",20,2000),
            new Employee("tt",26,1000)
    );
    @Test
    public void test1() {
        Collections.sort(employeeList,(x,y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(),y.getAge());
            }
        });

        for (Employee emp : employeeList
             ) {
            System.out.println(emp);
        }
    }
}
