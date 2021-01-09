package Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> filtered = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(w -> w.length() % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(String.join(System.lineSeparator(), filtered));
    }
}
