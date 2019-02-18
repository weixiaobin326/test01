package java8test.lambdatest01;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author weixiaobin
 * @Date 2019/1/17 14:07
 */
public class StreamAPITest2 {
    List<Employee> employeeList = Arrays.asList(
            new Employee("ww",26,4000),
            new Employee("qq",29,9000),
            new Employee("yy",27,4000),
            new Employee("uu",27,4000),
            new Employee("ee",20,2000),
            new Employee("tt",26,1000)
    );

    /**
     * 归约
     * @return
     */
    @Test
    public void test4() {
        Optional<Double> optional = employeeList.stream()
                .map(Employee::getSalary)
                .reduce((x, y) -> x + y);
        //System.out.println(optional.get());

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> reduce = list.stream().map((num) -> {
            if (num > 6) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);

        System.out.println(reduce.get());
    }

    /**
     * 终止操作(查找与匹配)
     */
    @Test
    public void test3() {
        //检查是否匹配所有
        Boolean bool = employeeList.stream()
                                   .allMatch((x) -> x.getAge() < 30);
        //任意匹配
        boolean bool1 = employeeList.stream()
                    .anyMatch(x -> x.getAge() == 30);
        //是否没有匹配的元素（没有匹配元素为TRUE，反之则为FALSE）
        boolean bool2 = employeeList.stream()
                    .noneMatch((x) -> "ww".equals(x.getName()));
        //查找
        //当该流为串行流是findAny与findFirst无异，当流为并行流时findAny才正真返回的是任意一个元素
        Optional<Employee> optional = employeeList.stream()
                                                  .parallel()
                                                  .filter((x) -> x.getAge() == 27)
                                                  .findAny();
        //查询当前流中的总数
        long count = employeeList.stream()
                .count();

        Optional<Integer> max = employeeList.stream()
                .map((x) -> x.getAge())
                .max((x, y) -> Double.compare(x, y));
        System.out.println(max);
    }

    @Test
    public void test() {
        List<String> list = Arrays.asList("aa","bb","cc");
        list.stream().map((str) -> str.toUpperCase())
                     .forEach(System.out::println);

        System.out.println("------------------------------");

        employeeList.stream()
                    .map(Employee::getName)
                    .forEach(System.out::println);

        Optional<Double> optional = employeeList.stream()
                                                .map(Employee::getSalary)
                                                .reduce(Double::sum);
        System.out.println(optional.get());

    }

    @Test
    public void Test02() {
        Double collect = employeeList.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect);

        Double collect1 = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(collect1);

        List<String> list = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(list);

        //导出工资表中最大的人的工资
        Optional<Double> optional = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy((x, y) -> Double.compare(x, y)));

        System.out.println(optional.get());




    }}
