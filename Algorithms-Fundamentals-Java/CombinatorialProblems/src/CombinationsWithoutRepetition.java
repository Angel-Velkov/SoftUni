import java.util.Scanner;

public class CombinationsWithoutRepetition {
    private static String[] elements;
    private static String[] slots;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        slots = new String[k];

        combination(0, 0);
    }

    private static void combination(int index, int start) {
        if (index >= slots.length) {
            print(slots);
        } else {
            for (int i = start; i < elements.length; i++) {
                slots[index] = elements[i];
                combination(index + 1, i + 1);
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
