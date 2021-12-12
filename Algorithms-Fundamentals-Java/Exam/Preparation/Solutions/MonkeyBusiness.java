import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MonkeyBusiness {

    private static int n;
    private static int[] sequence;
    private static int[] slots;
    private static StringBuilder result = new StringBuilder();
    private static int solutionsCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        sequence = IntStream.range(1, n + 1).toArray();
        slots = new int[n];
        combinations(0);
        System.out.println(result.toString().trim());
        System.out.println("Total Solutions: " + solutionsCount);
    }

    private static void combinations(int index) {
        if (index >= n) {
            if (Arrays.stream(slots).sum() == 0) {
                saveTheCombination();
            }
        } else {
            slots[index] = sequence[index];
            combinations(index + 1);
            slots[index] = -sequence[index];
            combinations(index + 1);
        }
    }

    private static void saveTheCombination() {
        solutionsCount++;
        for (int number : slots) {
            if (number > 0) {
                result.append("+");
            }
            result.append(number).append(" ");
        }
        result.append(System.lineSeparator());
    }
}
