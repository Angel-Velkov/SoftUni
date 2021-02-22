import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;

public class FindAllPathsInALabyrinth {

    private static final ArrayDeque<Character> path = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());
        char[][] labyrinth = readMatrix(rows, cols, scanner);

        findPaths(labyrinth, 0, 0, ' ');
    }

    private static void findPaths(char[][] labyrinth, int row, int col, char direction) {
        if (OutOfBounds(labyrinth, row, col)) {
            return;
        }
        if (direction != ' ') {
            path.push(direction);
        }
        if (labyrinth[row][col] == 'e') {
            printDirection();
        } else if (labyrinth[row][col] != 'V' && labyrinth[row][col] != '*') {
            //Mark
            labyrinth[row][col] = 'V';

            findPaths(labyrinth, row + 1, col, 'D');
            findPaths(labyrinth, row, col + 1, 'R');
            findPaths(labyrinth, row - 1, col, 'U');
            findPaths(labyrinth, row, col - 1, 'L');

            //unMark
            labyrinth[row][col] = '-';
        }
        if (!path.isEmpty())
            path.pop();
    }
    private static void printDirection() {
        Iterator<Character> iterator = path.descendingIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }

    private static boolean OutOfBounds(char[][] matrix, int row, int col) {
        return 0 > row || row >= matrix.length
                || 0 > col || col >= matrix[row].length;
    }

    private static char[][] readMatrix(int rows, int cols, Scanner scanner) {
        char[][] matrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = line.charAt(col);
            }
        }
        return matrix;
    }
}
