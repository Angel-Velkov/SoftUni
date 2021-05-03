package searchingAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = (String[]) Arrays.stream(scanner.nextLine().split(",\\s+")).toArray();
        String requiredString = scanner.nextLine();

        int index = contains(strings, requiredString);
        System.out.println(index);
    }

    private static int contains(String[] strings, String requiredString) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(requiredString)) {
                return i;
            }
        }

        return -1;
    }
}
