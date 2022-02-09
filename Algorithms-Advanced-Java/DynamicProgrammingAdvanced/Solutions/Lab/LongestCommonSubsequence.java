import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstWord = scanner.nextLine();
        String secondWord = scanner.nextLine();

        int[][] table = new int[firstWord.length() + 1][secondWord.length() + 1];

        for (int row = 1; row <= firstWord.length(); row++) {
            for (int col = 1; col <= secondWord.length(); col++) {
                if (firstWord.charAt(row - 1) == secondWord.charAt(col - 1)) {
                    table[row][col] = table[row - 1][col - 1] + 1;
                } else {
                    table[row][col] = Math.max(table[row - 1][col], table[row][col - 1]);
                }
            }
        }

        System.out.println(table[firstWord.length()][secondWord.length()]);

        Deque<Character> lcsLetters = new ArrayDeque<>();

        int row = firstWord.length();
        int col = secondWord.length();

        while (row > 0 && col > 0) {
            if (firstWord.charAt(row - 1) == secondWord.charAt(col - 1)) {
                lcsLetters.push(firstWord.charAt(row - 1));
                row--;
                col--;
            } else if (table[row - 1][col] > table[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }

        lcsLetters.forEach(System.out::print);
    }
}
