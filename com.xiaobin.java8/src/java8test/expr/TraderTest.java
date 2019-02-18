package java8test.expr;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author weixiaobin
 * @Date 2019/1/18 11:30
 */
public class TraderTest {
    List<Transaction> transactions;
    @Before
    public void before() {
        Trader zn = new Trader("猪年大吉","汕头");
        Trader zn1 = new Trader("猪年大吉1","汕头");
        Trader xn = new Trader("新年快乐","广州");
        Trader ws = new Trader("万事如意","惠州");

        transactions = Arrays.asList(
                new Transaction(zn, 2017, 2000),
                new Transaction(zn1, 2017, 2000),
                new Transaction(xn, 2017, 2000),
                new Transaction(ws, 2017, 2000),
                new Transaction(zn, 2018, 1000),
                new Transaction(xn, 2018, 2000),
                new Transaction(ws, 2018, 1000),
                new Transaction(ws, 2019, 7000)
        );
    }

    /**
     * 1. 找出2017年发生的所有交易， 并按交易额排序（从低到高）
     */
    @Test
    public void test01() {
        transactions.stream()
                    .filter((x) -> x.getYear() == 2017)
                    .sorted((x,y) -> Integer.compare(x.getValue(),y.getValue()))
                    .forEach(System.out::println);
    }
    /**
     * 2. 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test02() {
        transactions.stream()
                    .map((x) -> x.getTrader().getCity())
                    .distinct()
                    .forEach(System.out::println);
    }
    /**
     *  3. 查找所有来自剑桥的交易员，并按姓名排序
     */
    @Test
    public void test03() {
       transactions.stream()
                    .filter((x) -> x.getTrader().getCity().equals("汕头"))
                    .map((x) -> x.getTrader())
                    .sorted((x,y) -> x.getName().compareTo(y.getName()))
                    .distinct()
                    .forEach(System.out::println);
    }

    /**
     * 4. 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void test04() {
        transactions.stream()
                    .map((x) -> x.getTrader().getName())
                    .sorted((x,y) -> x.compareTo(y))
                    .distinct()
                    .forEach(System.out::println);
    }
    @Test
    public void test05() {
        transactions.stream()
                .map((x) -> x.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 5. 有没有交易员是在广州工作的？
     */
    @Test
    public void test06() {
        boolean b = transactions.stream()
                .anyMatch((x) -> x.getTrader().getCity().equals("广州"));
        System.out.println(b);
    }

    /**
     * 6.打印生活在剑桥的交易员的所有交易额
     */
    @Test
    public void test07() {
        IntSummaryStatistics collect = transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("汕头"))
                .collect(Collectors.summarizingInt((x) -> x.getValue()));

        System.out.println(collect.getSum());
    }

    @Test
    public void test08() {
        Optional<Integer> optional = transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("汕头"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(optional.get());
    }
    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test09() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max((x, y) -> x.compareTo(y));
        System.out.println(max.get());

    }
    //8. 找到交易额最小的交易
    @Test
    public void test10() {
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .min((x, y) -> x.compareTo(y));
        System.out.println(min.get());

    }

}
