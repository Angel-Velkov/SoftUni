import java.util.Scanner;

public class VariationsWithoutRepetitions {
    private static String[] elements;
    private static String[] slots;
    private static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        //Slots count
        int k = Integer.parseInt(scanner.nextLine());
        slots = new String[k];
        used = new boolean[elements.length];

        variation(0);
    }

    private static void variation(int index) {
        if (index >= slots.length) {
            print(slots);
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    slots[index] = elements[i];
                    variation(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
