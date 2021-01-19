package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] firstMatrix = readMatrix(scanner);
        int[][] secondMatrix = readMatrix(scanner);

        boolean isEqual = firstMatrix.length == secondMatrix.length;

        if (isEqual) {
            for (int row = 0; row < firstMatrix.length; row++) {
                int[] firstArr = firstMatrix[row];
                int[] secondArr = secondMatrix[row];
                isEqual = firstArr.length == secondArr.length;
                if (isEqual) {
                    for (int colon = 0; colon < firstArr.length; colon++) {
                        int firstElement = firstArr[colon];
                        int secondElement = secondArr[colon];
                        if (firstElement != secondElement) {
                            isEqual = false;
                            break;
                        }
                    }
                }
                if (!isEqual) {
                    break;
                }
            }
        }
        String output = isEqual ? "equal" : "not equal";
        System.out.println(output);

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
