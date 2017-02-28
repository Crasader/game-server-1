import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by veininguo on 2017/2/8.
 */
public class LambdaTest {

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    @Test
    public void testLambda() {
        Predicate<String> nonEmptyPredicate = (String s) -> s.isEmpty();
        List<String> list = Arrays.asList("aaa", "", "bbb");
        for (String str : list) {
            System.out.println(nonEmptyPredicate.test(str));
        }


        Consumer<Integer> forEach = (Integer i) -> System.out.println(i);
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        for (Integer number : numbers) {
            forEach.accept(number);
        }

        List<String> strings = Arrays.asList("aaa", "bbb");
        List<Integer> result = map(strings, (String s) -> s.length());
        System.out.println(result);
    }
}
