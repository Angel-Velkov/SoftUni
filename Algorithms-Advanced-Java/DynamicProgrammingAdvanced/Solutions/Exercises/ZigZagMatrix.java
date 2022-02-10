import java.util.*;
import java.util.stream.Collectors;

public class ZigZagMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        int[][] matrix = readMatrix(rows, scanner, "\\s*,\\s*");

        int[][] dp = new int[rows][cols];
        int[][] prev = new int[rows][cols];
        for (int[] row : prev) {
            Arrays.fill(row, -1);
        }

        for (int row = 0; row < rows; row++) {
            dp[row][0] = matrix[row][0];
        }

        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int currentMax = 0;

                if (col % 2 != 0) {
                    for (int innerRow = row + 1; innerRow < rows; innerRow++) {
                        if (dp[innerRow][col - 1] > currentMax) {
                            currentMax = dp[innerRow][col - 1];
                            prev[row][col] = innerRow;
                        }
                    }
                } else {
                    for (int innerRow = 0; innerRow < row; innerRow++) {
                        if (dp[innerRow][col - 1] > currentMax) {
                            currentMax = dp[innerRow][col - 1];
                            prev[row][col] = innerRow;
                        }
                    }
                }

                dp[row][col] = currentMax + matrix[row][col];
            }
        }

        int bestResult = 0;
        int row = -1;
        int col = cols - 1;

        for (int r = 0; r < rows; r++) {
            if (dp[r][col] > bestResult) {
                bestResult = dp[r][col];
                row = r;
            }
        }

        List<Integer> path = new ArrayList<>();

        while (row != -1) {
            path.add(matrix[row][col]);
            row = prev[row][col];
            col--;
        }

        Collections.reverse(path);

        System.out.println(
                bestResult + " = " + path.stream().map(String::valueOf).collect(Collectors.joining(" + "))
        );
    }

    private static int[][] readMatrix(int rows, Scanner scanner, String delimiter) {
        int[][] matrix = new int[rows][];

        for (int r = 0; r < rows; r++) {
            matrix[r] = Arrays.stream(scanner.nextLine().split(delimiter))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return matrix;
    }
}
