import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectedAreasInAMatrix {
    private static char[][] matrix;
    private static final AtomicInteger foundAreas = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        matrix = readMatrix(rows, cols, reader);

        List<int[]> coordinatesAndCount = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char symbol = matrix[row][col];
                if (symbol == '-') {
                    findArea(row, col);
                    coordinatesAndCount.add(new int[]{row, col, foundAreas.get()});
                    foundAreas.set(0);
                }
            }
        }

        System.out.println("Total areas found: " + coordinatesAndCount.size());
        AtomicInteger lineNum = new AtomicInteger();

        coordinatesAndCount.stream()
                .sorted((f, s) -> Integer.compare(s[2], f[2]))
                .forEach(c -> System.out.printf("Area #%d at (%d, %d), size: %d%n"
                        , lineNum.incrementAndGet(), c[0], c[1], c[2]));
    }

    private static void findArea(int row, int col) {
        if (isInBounds(row, col, matrix) && isTraversable(row, col, matrix)) {
            matrix[row][col] = 'V';
            //Size of the area
            foundAreas.incrementAndGet();

            //DOWN
            findArea(row + 1, col);
            //LEFT
            findArea(row, col + 1);
            //UP
            findArea(row - 1, col);
            //RIGHT
            findArea(row, col - 1);
        }
    }

    private static char[][] readMatrix(int rows, int cols, BufferedReader reader) {
        char[][] matrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            try {
                String line = reader.readLine();
                for (int col = 0; col < cols; col++) {
                    matrix[row][col] = line.charAt(col);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return matrix;
    }

    private static boolean isTraversable(int row, int col, char[][] matrix) {
        char symbol = matrix[row][col];
        return symbol != 'V' && symbol != '*';
    }

    private static boolean isInBounds(int row, int col, char[][] matrix) {
        return 0 <= row && row < matrix.length
                && 0 <= col && col < matrix[row].length;
    }
}
