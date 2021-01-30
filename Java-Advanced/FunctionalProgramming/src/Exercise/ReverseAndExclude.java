package Exercise;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BiFunction<Integer, Integer, Boolean> isNotDivisibleBy = (n, d) -> (n % d) != 0;
        Function<List<Integer>, String> arrayAsString =
                nums -> nums.stream().map(String::valueOf).collect(Collectors.joining(" "));

        List<Integer> numbers = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.reverse(numbers);
        int divisor = Integer.parseInt(scanner.nextLine());

        numbers = numbers.stream().filter(n -> isNotDivisibleBy.apply(n, divisor)).collect(Collectors.toList());

        System.out.println(arrayAsString.apply(numbers));


         /*
        List<Integer> numbers = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.reverse(numbers);

        int divisor = Integer.parseInt(scanner.nextLine());

        Predicate<Integer> isNotDivisibleBy = n -> n % divisor != 0;

        numbers.stream().filter(isNotDivisibleBy).forEach(n -> System.out.print(n + " "));

          */
    }
}
