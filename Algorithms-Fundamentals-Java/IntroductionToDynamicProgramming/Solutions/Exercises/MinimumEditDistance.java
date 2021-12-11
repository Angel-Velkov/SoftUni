import java.util.Scanner;

public class MinimumEditDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int costReplace = Integer.parseInt(scanner.nextLine());
        int costInsert = Integer.parseInt(scanner.nextLine());
        int costDelete = Integer.parseInt(scanner.nextLine());

        char[] first = scanner.nextLine().toCharArray();
        char[] second = scanner.nextLine().toCharArray();

        int[][] table = new int[first.length + 1][second.length + 1];

        // Fill with insertion cost
        for (int c = 1; c < table[0].length; c++) {
            table[0][c] += table[0][c - 1] + costInsert;
        }

        // Fill with deletion cost
        for (int r = 1; r < table.length; r++) {
            table[r][0] += table[r - 1][0] + costDelete;
        }

        for (int r = 1; r < table.length; r++) {
            for (int c = 1; c < table[r].length; c++) {
                if (first[r - 1] == second[c - 1]) {
                    table[r][c] = table[r - 1][c - 1];
                } else {
                    int insert = table[r][c - 1] + costInsert;
                    int delete = table[r - 1][c] + costDelete;
                    int replace = table[r - 1][c - 1] + costReplace;

                    table[r][c] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }

        System.out.println("Minimum edit distance: " + table[first.length][second.length]);
    }
}
