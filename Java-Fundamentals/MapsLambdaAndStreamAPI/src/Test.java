import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;


public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(123, 596, 70));

        numbers.stream().flatMap(new Function<Integer, Stream<?>>() {
            @Override
            public Stream<?> apply(Integer integer) {
                List<Integer> digits = new ArrayList<>();
                while (integer > 0) {
                    digits.add(integer % 10);
                    integer /= 10;
                }

                Collections.reverse(digits);
                return digits.stream();
            }
        }).forEach(d -> System.out.println(d));
    }
}
