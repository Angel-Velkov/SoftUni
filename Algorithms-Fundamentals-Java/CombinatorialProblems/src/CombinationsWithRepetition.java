import java.util.Scanner;

public class CombinationsWithRepetition {
    private static String[] elements;
    private static String[] slots;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        slots = new String[k];

        combinationWithRep(0, 0);
    }

    private static void combinationWithRep(int index, int start) {
        if (index >= slots.length) {
            print(slots);
        } else {
            for (int i = start; i < elements.length; i++) {
                slots[index] = elements[i];
                //The difference between this one and the oder is "i".
                //Here we use only "i" and there we use "i + 1" (in the recursion call)
                combinationWithRep(index + 1, i);
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}

