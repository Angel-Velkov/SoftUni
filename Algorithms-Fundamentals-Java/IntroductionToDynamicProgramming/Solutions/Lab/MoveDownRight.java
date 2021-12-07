import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MoveDownRight {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[n][];

        for (int row = 0; row < n; row++) {
            matrix[row] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

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

        List<int[]> coordinates = new ArrayList<>();

        int row = n - 1;
        int col = m - 1;

        coordinates.add(new int[]{row, col});

        while (row != 0 || col != 0) {
            if (col == 0 ) {
                row--;
            } else if (row == 0) {
                col--;
            } else if (pathSums[row - 1][col] > pathSums[row][col - 1]) {
                row--;
            } else {
                col--;
            }

            coordinates.add(new int[]{row, col});
        }

        Collections.reverse(coordinates);

        System.out.println(
                coordinates
                        .stream()
                        .map(array -> Arrays.stream(array)
                                .boxed()
                                .map(String::valueOf)
                                .collect(Collectors.joining(", ", "[", "]")))
                        .collect(Collectors.joining(" "))
        );
    }
}
