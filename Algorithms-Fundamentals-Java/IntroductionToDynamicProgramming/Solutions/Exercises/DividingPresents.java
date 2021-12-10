import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DividingPresents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] presents = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalSum = Arrays.stream(presents).sum();
        int median = totalSum / 2;

        Map<Integer, Integer> allPossibleSums = findAllPossibleSums(presents);
        int alanSum = findTheSumOfAlanPresents(allPossibleSums, median);
        int bobSum = totalSum - alanSum;
        List<Integer> alanPresents = findAlanPresents(allPossibleSums, alanSum);

        printTheOutput(totalSum, alanSum, bobSum, alanPresents);
    }

    private static void printTheOutput(int totalSum, int alanSum, int bobSum, List<Integer> alanPresents) {
        System.out.printf("Difference: %d%n" +
                        "Alan:%d Bob:%d%n" +
                        "Alan takes: %s%n" +
                        "Bob takes the rest.",
                (bobSum - alanSum),
                alanSum, (totalSum - alanSum),
                alanPresents
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    private static int findTheSumOfAlanPresents(Map<Integer, Integer> allPossibleSums, int median) {
        int alanSum = median;

        while (!allPossibleSums.containsKey(alanSum)) {
            alanSum--;
        }

        return alanSum;
    }

    private static List<Integer> findAlanPresents(Map<Integer, Integer> presents, int alanSum) {
        List<Integer> alanPresents = new ArrayList<>();

        while (alanSum > 0) {
            int present = presents.get(alanSum);
            alanPresents.add(present);
            alanSum -= present;
        }

        return alanPresents;
    }

    private static Map<Integer, Integer> findAllPossibleSums(int[] numbers) {
        Map<Integer, Integer> possibleSums = new HashMap<>();
        possibleSums.put(0, 0);

        for (int number : numbers) {
            for (int sum : new ArrayList<>(possibleSums.keySet())) {
                possibleSums.putIfAbsent(sum + number, number);
            }
        }

        return possibleSums;
    }
}
