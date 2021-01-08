package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> result = new ArrayList<>();

        String[] input = scanner.nextLine().split("\\|");

        for (int i = input.length - 1; i >= 0; i--) {
            String[] numbers = input[i].split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                if (!numbers[j].equals("")) {
                    result.add(numbers[j]);
                }
            }
        }
        for (String s : result) {
            System.out.print(s + " ");
        }
    }
}
