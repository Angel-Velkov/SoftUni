import java.util.Scanner;

public class AbaspaBasapa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        int[][] dp = new int[first.length() + 1][second.length() + 1];


        int bestLength = 0;
        int lastIndex = -1;

        for (int row = 1; row <= first.length(); row++) {
            for (int col = 1; col <= second.length(); col++) {

                if (first.charAt(row - 1) == second.charAt(col - 1)) {

                    dp[row][col] = dp[row - 1][col - 1] + 1;
                    if (dp[row][col] > bestLength) {
                        bestLength = dp[row][col];
                        lastIndex = row - 1;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while (bestLength-- > 0) {
            result.append(first.charAt(lastIndex--));
        }

        result.reverse();

        System.out.println(result);
    }
}
