package java8test.tactics;

import java8test.lambdatest01.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author weixiaobin
 * @Date 2019/1/16 14:59
 */
public class LambdaTest {
    List<Employee> employeeList = Arrays.asList(
            new Employee("qq",29,9000),
            new Employee("ww",27,4000),
            new Employee("ee",20,2000),
            new Employee("tt",26,1000)
    );

    public List<Employee> judge(List<Employee> employeeList,EmployeeInterface<Employee> employeeInterface) {
        ArrayList<Employee> list = new ArrayList<>();


        for (Employee employee : employeeList
             ) {
            if(employeeInterface.task(employee)){
                list.add(employee);
            }
        }

        return list;
    }

    @Test
    public void test01() {
        EmployeeImpl employeeImpl = new EmployeeImpl();
        List<Employee> list = judge(employeeList, employeeImpl);

        for (Employee employee : list
             ) {
            System.out.println(employee);
        }
    }

    @Test
    public void test02() {
        List<Employee> list = judge(employeeList, new EmployeeInterface<Employee>() {

            @Override
            public boolean task(Employee employee) {
                return employee.getSalary() < 2000;
            }
        });

        for (Employee employee : list
                ) {
            System.out.println(employee);
        }
    }

}
