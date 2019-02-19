package java8test.lambdatest01;

import org.junit.Test;

/**
 * @Author weixiaobin
 * @Date 2019/1/16 16:56
 */
public class TestLambda {
    public String option(String str,Function01 f) {
        return f.getValue(str);
    }

    public void opration01(Long l1,Long l2,Function02<Long,Long> f) {
        System.out.println(f.operation(l1,l2));
    }

    @Test
    public void Test01() {
        String s = option("abcdefg", x -> x.toUpperCase());

        System.out.println(s);

        String s1 = option("abcdefg", x -> x.substring(2, 5));
        System.out.println(s1);

    }

    @Test
    public void Test02() {
        opration01(200L,100L,(n1,n2) -> n1+n2);

        opration01(200L,100L,(n1,n2) -> n1*n2);
    }
}
