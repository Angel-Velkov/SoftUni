import java.util.Scanner;

public class WordDifferences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] first = scanner.nextLine().toCharArray();
        char[] second = scanner.nextLine().toCharArray();

        int[][] table = new int[first.length + 1][second.length + 1];

        for (int r = 0; r < table.length; r++) {
            for (int c = 0; c < table[r].length; c++) {
                if (r == 0) {
                    table[r][c] = c;
                } else if (c == 0) {
                    table[r][c] = r;
                } else if (first[r - 1] == second[c - 1]) {
                    table[r][c] = table[r - 1][c - 1];
                } else {
                    table[r][c] = Math.min(table[r - 1][c], table[r][c - 1]) + 1;
                }
            }
        }

        System.out.println("Deletions and insertions: " + table[first.length][second.length]);
    }
}
