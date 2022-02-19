import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Time {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] first = parseIntArray(reader.readLine(), "\\s+");
        int[] second = parseIntArray(reader.readLine(), "\\s+");

        Deque<Integer> longestCommonSubsequence = findLCS(first, second);

        System.out.println(
                longestCommonSubsequence
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );

        System.out.println(longestCommonSubsequence.size());

    }

    private static Deque<Integer> findLCS(int[] first, int[] second) {
        int[][] lcs = new int[first.length + 1][second.length + 1];

        for (int row = 1; row <= first.length; row++) {
            for (int col = 1; col <= second.length; col++) {
                if (first[row - 1] == second[col - 1]) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                } else {
                    lcs[row][col] = Math.max(lcs[row - 1][col], lcs[row][col - 1]);
                }
            }
        }

        Deque<Integer> result = new ArrayDeque<>();

        int row = first.length;
        int col = second.length;

        while (row > 0 && col > 0) {
            if (first[row - 1] == second[col - 1]) {
                result.push(first[row - 1]);
                row--;
                col--;
            } else if (lcs[row - 1][col] > lcs[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }

        return result;
    }

    private static int[] parseIntArray(String line, String separator) throws IOException {
        return Arrays.stream(line.split(separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
