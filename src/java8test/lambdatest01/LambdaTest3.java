package java8test.lambdatest01;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @Author weixiaobin
 * @Date 2019/1/17 9:21
 */
public class LambdaTest3 {
    /*
     * Java8 内置的四大核心函数式接口
     *
     * Consumer<T> : 消费型接口
     * 		void accept(T t);
     *
     * Supplier<T> : 供给型接口
     * 		T get();
     *
     * Function<T, R> : 函数型接口
     * 		R apply(T t);
     *
     * Predicate<T> : 断言型接口
     * 		boolean test(T t);
     *
     */



    @Test
    public void test01() {
        happy(100.0,(x) -> System.out.println("消费了" + x));
    }

    /**
     * @param money
     * @param consumer
     */
    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
