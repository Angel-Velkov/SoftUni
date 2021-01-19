package Lab;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int colons = Integer.parseInt(scanner.nextLine());

        char[][] firstMatrix = readMatrix(scanner, rows, colons);
        char[][] secondMatrix = readMatrix(scanner, rows, colons);

        char[][] finalMatrix = new char[rows][colons];
        for (int row = 0; row < rows; row++) {
            for (int colon = 0; colon < colons; colon++) {
                char firstSymbol = firstMatrix[row][colon];
                char secondSymbol = secondMatrix[row][colon];

                finalMatrix[row][colon] = firstSymbol == secondSymbol ? firstSymbol : '*';
            }
        }

        printMatrix(finalMatrix);
    }

    private static char[][] readMatrix(Scanner scanner, int rows, int colons) {
        char[][] matrix = new char[rows][colons];
        for (int row = 0; row < matrix.length; row++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int colon = 0; colon < line.length; colon++) {
                matrix[row][colon] = line[colon].charAt(0);
            }
        }
        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int colon = 0; colon < matrix[row].length; colon++) {
                System.out.print(matrix[row][colon] + " ");
            }
            System.out.println();
        }
    }
}
