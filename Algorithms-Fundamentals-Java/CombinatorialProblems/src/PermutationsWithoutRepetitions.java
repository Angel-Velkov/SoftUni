import java.util.Scanner;

public class PermutationsWithoutRepetitions {
    private static String[] elements;
    private static String[] permutations;
    private static boolean[] used;

    public static void main(String[] args) {

        elements = new Scanner(System.in).nextLine().split("\\s+");
        permutations = new String[elements.length];
        used = new boolean[elements.length];

        permute(0);
    }

    private static void permute(int index) {
        if (index >= permutations.length) {
            print(permutations);
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    permutations[index] = elements[i];
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
