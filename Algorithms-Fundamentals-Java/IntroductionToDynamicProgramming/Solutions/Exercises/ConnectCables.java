import java.util.Arrays;
import java.util.Scanner;

public class ConnectCables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cables = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] destination = new int[cables.length];
        for (int i = 0; i < destination.length; i++) {
            destination[i] = i + 1;
        }

        int[][] table = new int[cables.length + 1][destination.length + 1];

        for (int r = 1; r < table.length; r++) {
            for (int c = 1; c < table[r].length; c++) {
                if (cables[r - 1] == destination[c - 1]) {
                    table[r][c] = table[r - 1][c - 1] + 1;
                } else {
                    table[r][c] = Math.max(table[r - 1][c], table[r][c - 1]);
                }
            }
        }

        System.out.println("Maximum pairs connected: " + table[cables.length][destination.length]);
    }
}
