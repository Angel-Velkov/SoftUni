import java.util.*;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] length = new int[sequence.length];
        int[] previous = new int[sequence.length];
        Arrays.fill(previous, -1);

        int maxLength = 0;
        int index = -1;

        for (int i = 0; i < sequence.length; i++) {
            int bestLength = 1;

            for (int j = i; j >= 0; --j) {
                if (sequence[j] < sequence[i] && length[j] + 1 >= bestLength) {
                    bestLength = length[j] + 1;
                    previous[i] = j;
                }
            }
            length[i] = bestLength;

            if (bestLength > maxLength) {
                maxLength = bestLength;
                index = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (index != -1) {
            result.add(sequence[index]);
            index = previous[index];
        }

        Collections.reverse(result);

        System.out.println(
                result
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }
}
