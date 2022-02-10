import java.util.*;
import java.util.stream.Collectors;

public class TheMadGardener {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] lis = new int[sequence.length];
        Arrays.fill(lis, 1);

        int[] lisPrev = new int[sequence.length];
        Arrays.fill(lisPrev, -1);

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] >= sequence[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    lisPrev[i] = j;
                }
            }
        }

        int[] lds = new int[sequence.length];
        Arrays.fill(lds, 1);

        int[] ldsPrev = new int[sequence.length];
        Arrays.fill(ldsPrev, -1);

        for (int i = sequence.length - 2; i >= 0; i--) {
            for (int j = sequence.length - 1; j > i; j--) {
                if (sequence[i] >= sequence[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                    ldsPrev[i] = j;
                }
            }
        }

        int highestPeak = 0;
        int peakIndex = -1;

        for (int i = 0; i < sequence.length; i++) {
            int currentPeak = lis[i] + lds[i] - 1;
            if (currentPeak >= highestPeak) {
                highestPeak = currentPeak;
                peakIndex = i;
            }
        }

        List<Integer> flowers = new ArrayList<>();

        int prevIndex = peakIndex;
        while (prevIndex != -1) {
            flowers.add(sequence[prevIndex]);
            prevIndex = lisPrev[prevIndex];
        }

        Collections.reverse(flowers);

        prevIndex = peakIndex;
        while (ldsPrev[prevIndex] != -1) {
            prevIndex = ldsPrev[prevIndex];
            flowers.add(sequence[prevIndex]);
        }

        System.out.println(
                flowers
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );

        System.out.printf("%.2f%n",
                flowers
                        .stream()
                        .mapToInt(i -> i)
                        .average()
                        .orElse(0)
        );

        System.out.println(flowers.size());
    }
}
