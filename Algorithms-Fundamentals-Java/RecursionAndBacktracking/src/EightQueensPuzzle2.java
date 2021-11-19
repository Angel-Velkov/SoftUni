import java.util.HashSet;
import java.util.Set;

public class EightQueensPuzzle2 {

    private final static int boardSize = 8;

    private static final char[][] board = generateMatrix(boardSize, boardSize, '-');
    private static final Set<Integer> usedCols = new HashSet<>();
    private static final Set<Integer> usedLeftDiag = new HashSet<>();
    private static final Set<Integer> usedRightDiag = new HashSet<>();

    public static void main(String[] a) {

        putQueen(0);
    }

    private static void putQueen(int row) {
        if (row >= boardSize) {
            printMatrix(board);
            System.out.println();
        } else {
            for (int col = 0; col < boardSize; col++) {
                if (canPlaceAQueen(row, col)) {
                    board[row][col] = '*';
                    addCoordinates(row, col);

                    putQueen(row + 1);

                    board[row][col] = '-';
                    removeCoordinates(row, col);
                }
            }
        }
    }

    private static boolean canPlaceAQueen(int row, int col) {
        int leftDiagonal = row - col;
        int rightDiagonal = row + col;

        return !usedCols.contains(col)
                && !usedLeftDiag.contains(leftDiagonal)
                && !usedRightDiag.contains(rightDiagonal);
    }

    private static void addCoordinates(int row, int col) {
        usedCols.add(col);
        usedLeftDiag.add(row - col);
        usedRightDiag.add(row + col);
    }

    private static void removeCoordinates(int row, int col) {
        usedCols.remove(col);
        usedLeftDiag.remove(row - col);
        usedRightDiag.remove(row + col);
    }

    public static char[][] generateMatrix(int rows, int cols, char symbol) {
        char[][] matrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = symbol;
            }
        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
