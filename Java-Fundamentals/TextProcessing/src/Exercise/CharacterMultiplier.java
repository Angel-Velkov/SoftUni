package Exercise;

import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.next();
        String second = scanner.next();

        int sum = sumOfMultipliedCharacters(first, second);

        System.out.println(sum);
    }

    private static int sumOfMultipliedCharacters(String first, String second) {
        int sum = 0;
        int max = Math.max(first.length(), second.length());
        int min = Math.min(first.length(), second.length());

        for (int i = 0; i < max; i++) {
            if (i < min) {
                sum += first.charAt(i) * second.charAt(i);
            } else if (first.length() == max) {
                sum += first.charAt(i);
            } else {
                sum += second.charAt(i);
            }
        }
        return sum;
    }
}
