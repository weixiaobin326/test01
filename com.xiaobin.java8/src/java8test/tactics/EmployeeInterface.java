package java8test.tactics;

/**
 * @Author weixiaobin
 * @Date 2019/1/16 14:51
 */
public interface EmployeeInterface<T> {
    /**
     * 判断传进来的对象是否符合条件
     * @param t 对象
     * @return
     */
    boolean task(T t);
}
