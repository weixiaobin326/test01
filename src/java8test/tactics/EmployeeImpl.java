package java8test.tactics;

import java8test.lambdatest01.Employee;

/**
 * @Author weixiaobin
 * @Date 2019/1/16 14:55
 */
public class EmployeeImpl implements EmployeeInterface<Employee>{
    /**
     * 判断工资是否高于2000
     * @param employee
     * @return
     */
    @Override
    public boolean task(Employee employee) {
        return employee.getSalary() > 2000;
    }
}
