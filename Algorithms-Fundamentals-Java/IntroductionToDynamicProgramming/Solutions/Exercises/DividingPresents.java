import java.util.*;
import java.util.stream.Collectors;

public class DividingPresents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] presents = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalSum = Arrays.stream(presents).sum();
        int median = totalSum / 2;

        Map<Integer, Integer> allPossibleSums = findAllPossibleSums(presents);
        int alanSum = findTheSumOfAlanPresents(allPossibleSums, median);
        List<Integer> alanPresents = findAlanPresents(allPossibleSums, alanSum);

        int bobSum = totalSum - alanSum;

        System.out.println("Difference: " + (bobSum - alanSum));
        System.out.println("Alan: " + alanSum + " Bob: " + (totalSum - alanSum));
        System.out.println("Alan takes: " +
                alanPresents
                        .stream().map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
        System.out.println("Bob takes the rest.");
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
            for (Integer sum : new ArrayList<>(possibleSums.keySet())) {
                possibleSums.putIfAbsent(sum + number, number);
            }
        }

        return possibleSums;
    }
}
