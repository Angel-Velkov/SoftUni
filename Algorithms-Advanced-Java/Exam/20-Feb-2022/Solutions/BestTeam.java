import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BestTeam {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] sequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(
                findLongestIncreasingOrDecreasingSubsequence(sequence)
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    private static List<Integer> findLongestIncreasingOrDecreasingSubsequence(int[] sequence) {
        int[] lisLength = new int[sequence.length];
        int[] ldsLength = new int[sequence.length];

        int[] lisPrev = new int[sequence.length];
        int[] ldsPrev = new int[sequence.length];

        Arrays.fill(lisPrev, -1);
        Arrays.fill(ldsPrev, -1);

        int maxIncreasingLength = 0;
        int lisIndex = -1;

        int maxDecreasingLength = 0;
        int ldsIndex = -1;

        for (int i = 0; i < sequence.length; i++) {
            int bestIncreasingLength = 1;
            int bestDecreasingLength = 1;

            for (int j = 0; j < i; j++) {
                if (sequence[i] >= sequence[j]) {
                    if (lisLength[j] + 1 > bestIncreasingLength) {
                        bestIncreasingLength = lisLength[j] + 1;
                        lisPrev[i] = j;
                    }
                } else {
                    if (ldsLength[j] + 1 >= bestDecreasingLength) {
                        bestDecreasingLength = ldsLength[j] + 1;
                        ldsPrev[i] = j;
                    }
                }
            }

            lisLength[i] = bestIncreasingLength;

            if (bestIncreasingLength > maxIncreasingLength) {
                maxIncreasingLength = bestIncreasingLength;
                lisIndex = i;
            }

            ldsLength[i] = bestDecreasingLength;

            if (bestDecreasingLength > maxDecreasingLength) {
                maxDecreasingLength = bestDecreasingLength;
                ldsIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        if (lisIndex == ldsIndex && ldsIndex == -1) {
            result.add(sequence[0]);
            return result;
        }

        if (maxIncreasingLength > maxDecreasingLength) {
            while (lisIndex != -1) {
                result.add(sequence[lisIndex]);
                lisIndex = lisPrev[lisIndex];
            }
        } else {
            while (ldsIndex != -1) {
                result.add(sequence[ldsIndex]);
                ldsIndex = ldsPrev[ldsIndex];
            }
        }

        Collections.reverse(result);

        return result;
    }
}
