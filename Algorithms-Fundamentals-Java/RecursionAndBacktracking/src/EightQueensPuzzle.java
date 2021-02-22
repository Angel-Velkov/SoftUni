public class EightQueensPuzzle {

    private final static int boardSize = 8;

    private static final char[][] board = generateMatrix(boardSize, boardSize, '-');

    public static void main(String[] args) {

        putQueens(0);
    }

    private static void putQueens(int row) {
        if (row == boardSize) {
            printMatrix(board);
            System.out.println();
        } else {
            for (int col = 0; col < boardSize; col++) {
                if (canPlaceAQueen(row, col)) {
                    board[row][col] = '*';
                    putQueens(row + 1);
                    board[row][col] = '-';
                }
            }
        }
    }

    private static boolean canPlaceAQueen(int row, int col) {
        for (int r = 0; r < boardSize; r++) {
            if (board[r][col] == '*') {
                return false;
            }
        }

        for (int c = 0; c < boardSize; c++) {
            if (board[row][c] == '*') {
                return false;
            }
        }

        int r = row;
        int c = col;

        while (0 <= r && 0 <= c) {
            if (board[r--][c--] == '*') {
                return false;
            }
        }

        r = row;
        c = col;

        while (0 <= r && c < boardSize) {
            if (board[r--][c++] == '*') {
                return false;
            }
        }

        r = row;
        c = col;

        while (r < boardSize && c < boardSize) {
            if (board[r++][c++] == '*') {
                return false;
            }
        }

        r = row;
        c = col;

        while (r < boardSize && 0 <= c) {
            if (board[r++][c--] == '*') {
                return false;
            }
        }
        return true;
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
