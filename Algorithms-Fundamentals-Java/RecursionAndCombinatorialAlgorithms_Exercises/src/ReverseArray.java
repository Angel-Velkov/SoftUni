import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] elements = scanner.nextLine().split("\\s+");

        printReverseArray(elements, elements.length - 1);
    }

    private static void printReverseArray(String[] numbers, int index) {
        if (index < 0) {
            return;
        }
        System.out.print(numbers[index] + " ");
        printReverseArray(numbers, index - 1);
    }
}
