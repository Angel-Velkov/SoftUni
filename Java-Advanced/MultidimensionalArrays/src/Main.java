import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] rowsAndColons = readArray(scanner);

        int rows = rowsAndColons[0];
        int colons = rowsAndColons[1];

        int[][] matrix = new int[rows][colons];
        for (int i = 0; i < rows; i++) {
            matrix[i] = readArray(scanner);
        }

        for (int row = 0; row < matrix.length; row++) {
            int[] arr = matrix[row];
            for (int colon = 0; colon < arr.length; colon++) {
                System.out.print(arr[colon] + " ");
            }
            System.out.println();
        }
    }

    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
