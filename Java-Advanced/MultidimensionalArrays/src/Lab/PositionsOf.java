package Lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = readMatrix(scanner);
        int num = Integer.parseInt(scanner.nextLine());
        List<int[]> coordinates = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int colon = 0; colon < matrix[row].length; colon++) {
                if (matrix[row][colon] == num) {
                    int[] indexes = {row, colon};
                    coordinates.add(indexes);
                }
            }
        }

        if (coordinates.isEmpty()) {
            System.out.println("not found");
        } else {
            for (int[] coordinate : coordinates) {
                System.out.println(coordinate[0] + " " + coordinate[1]);
            }
        }
    }

    private static int[][] readMatrix(Scanner scanner) {
        int[] rowsAndColons = readArray(scanner);

        int rows = rowsAndColons[0];
        int colons = rowsAndColons[1];

        int[][] matrix = new int[rows][colons];
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = readArray(scanner);
        }
        return matrix;
    }

    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
