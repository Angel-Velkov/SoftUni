package November2020;

import java.util.*;

public class Loot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] initialTreasureChest = scanner.nextLine().split("\\|");
        List<String> treasure = new ArrayList<>(Arrays.asList(initialTreasureChest));

        String input;

        while (!"Yohoho!".equals(input = scanner.nextLine())) {
            String[] inputAsArray = input.split(" ");
            String command = inputAsArray[0];

            switch (command) {
                case "Loot":
                    for (int i = 1; i < inputAsArray.length; i++) {
                        if (!treasure.contains(inputAsArray[i])) {
                            treasure.add(0, inputAsArray[i]);
                        }
                    }
                    break;
                case "Drop":
                    int index = Integer.parseInt(inputAsArray[1]);
                    if (0 <= index && index < treasure.size()) {
                        treasure.add(treasure.remove(index));
                    }
                    break;
                case "Steal":
                    int count = Integer.parseInt(inputAsArray[1]);
                    count = Math.max(treasure.size() - count, 0);
                    List<String> stolenItems = new ArrayList<>();
                    for (int i = treasure.size() - 1; i >= count; i--) {
                        stolenItems.add(treasure.remove(i));
                    }
                    Collections.reverse(stolenItems);
                    System.out.println(String.join(", ", stolenItems));
                    break;
            }
        }
        if (treasure.isEmpty()) {
            System.out.println("Failed treasure hunt.");
        } else {
            int sum = 0;
            for (String s : treasure) {
                sum += s.length();
            }
            double averageGain = sum / (double) treasure.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.", averageGain);
        }
    }
}
