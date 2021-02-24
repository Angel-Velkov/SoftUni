import java.util.Scanner;

public class VariationsWithRepetition {
    private static String[] elements;
    private static String[] slots;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        //Slots count
        int k = Integer.parseInt(scanner.nextLine());
        slots = new String[k];
        variationWithRep(0);
    }

    private static void variationWithRep(int index) {
        if (index >= slots.length) {
            print(slots);
        } else {
            for (String element : elements) {
                slots[index] = element;
                variationWithRep(index + 1);
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
