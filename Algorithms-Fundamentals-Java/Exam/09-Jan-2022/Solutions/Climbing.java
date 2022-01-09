import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Climbing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());
        int m = Integer.parseInt(reader.readLine().trim());

        int[][] matrix = readMatrix(n, reader, "\\s+");

        int[][] pathSums = new int[n][m];
        pathSums[0][0] = matrix[0][0];

        for (int col = 1; col < m; col++) {
            pathSums[0][col] = pathSums[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < n; row++) {
            pathSums[row][0] = pathSums[row - 1][0] + matrix[row][0];
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                int up = pathSums[row - 1][col];
                int left = pathSums[row][col - 1];

                pathSums[row][col] = Math.max(up, left) + matrix[row][col];
            }
        }

        List<Integer> path = new ArrayList<>();

        int row = n - 1;
        int col = m - 1;

        path.add(matrix[row][col]);

        while (row != 0 || col != 0) {
            if (col == 0) {
                row--;
            } else if (row == 0) {
                col--;
            } else if (pathSums[row - 1][col] > pathSums[row][col - 1]) {
                row--;
            } else {
                col--;
            }

            path.add(matrix[row][col]);
        }

        System.out.println(pathSums[n - 1][m - 1]);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }

    private static int[][] readMatrix(int rows, BufferedReader reader, String separator) throws IOException {
        int[][] matrix = new int[rows][];

        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = Arrays.stream(reader.readLine().trim().split(separator))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return matrix;
    }
}
