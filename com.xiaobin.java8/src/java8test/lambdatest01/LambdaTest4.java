package java8test.lambdatest01;

/**
 * @Author weixiaobin
 * @Date 2019/1/17 10:06
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 */
public class LambdaTest4 {
    @Test
    public void Test01() {
        Consumer<String> con = (s) -> System.out.println(s);
        con.accept("shuchu");

        Consumer<String> con1 = System.out::println;
        con.accept("shuru");

    }

    @Test
    public void Test02() {
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);

        int compare = com.compare(4, 4);

        System.out.println(compare);

        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com1.compare(4,4));


    }
}
