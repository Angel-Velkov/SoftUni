public class EightQueensPuzzle3 {
    public static void main(String[] args) {
        int boardSize = 8;
        enumerate(boardSize);
    }

    public static void enumerate(int boardSize) {
        int[] q = new int[boardSize];
        enumerate(q, 0);
    }

    /**
     * @param q          the columns on which a queen can be placed. The indices are the rows.
     * @param currentRow the index of the current row, which we check if we can put a queen.
     */
    public static void enumerate(int[] q, int currentRow) {
        int rows = q.length;

        if (currentRow == rows) {
            printQueens(q);
        } else {
            for (int col = 0; col < rows; col++) {
                q[currentRow] = col;

                if (isConsistent(q, currentRow)) {
                    enumerate(q, currentRow + 1);
                }
            }
        }
    }

    public static void printQueens(int[] q) {
        int n = q.length;
        for (int k : q) {
            for (int j = 0; j < n; j++) {
                if (k == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param q  the columns on which a queen can be placed. The indices are the rows.
     * @param r1 current row.
     */
    public static boolean isConsistent(int[] q, int r1) {
        int c1 = q[r1];

        for (int r2 = 0; r2 < r1; r2++) {
            int c2 = q[r2];

            if (c2 == c1) return false;                 // same column
            if ((c2 - c1) == (r1 - r2)) return false;   // same major diagonal
            if ((c1 - c2) == (r1 - r2)) return false;   // same minor diagonal
        }
        return true;
    }
}
