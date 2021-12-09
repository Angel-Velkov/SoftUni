import java.util.*;
import java.util.stream.Collectors;

public class LongestZigzagSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        // zigzagLength[i][0] - last element is grater than its previous
        // zigzagLength[i][1] - last element is smaller than its previous
        int[][] zigzagLength = new int[sequence.length][2];
        for (int i = 0; i < sequence.length; i++) {
            zigzagLength[i][0] = zigzagLength[i][1] = 1;
        }

        int[][] previous = new int[sequence.length][2];
        for (int i = 0; i < sequence.length; i++) {
            previous[i][0] = previous[i][1] = -1;
        }

        int bestLength = 1;
        int index = 0;

        for (int i = 0; i < sequence.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (sequence[i] > sequence[j] && zigzagLength[i][0] <= zigzagLength[j][1] + 1) {
                    zigzagLength[i][0] = zigzagLength[j][1] + 1;
                    previous[i][0] = j;
                }

                if (sequence[i] < sequence[j] && zigzagLength[i][1] <= zigzagLength[j][0] + 1) {
                    zigzagLength[i][1] = zigzagLength[j][0] + 1;
                    previous[i][1] = j;
                }
            }

            int currentMaxLength = Math.max(zigzagLength[i][0], zigzagLength[i][1]);
            if (bestLength < currentMaxLength) {
                bestLength = currentMaxLength;
                index = i;
            }
        }

        List<Integer> result = getLongestZigZagSubsequence(sequence, zigzagLength, previous, index);

        System.out.println(
                result
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    private static List<Integer> getLongestZigZagSubsequence(int[] sequence, int[][] zigzagLength, int[][] previous, int index) {
        List<Integer> result = new ArrayList<>();

        int start = zigzagLength[index][0] > zigzagLength[index][1] ? 0 : 1;

        while (index != -1) {
            result.add(sequence[index]);

            if (start == 0) {
                index = previous[index][0];
                start = 1;
            } else {
                index = previous[index][1];
                start = 0;
            }
        }

        Collections.reverse(result);

        return result;
    }
}
