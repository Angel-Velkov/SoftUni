import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class KnapsackIterative {
    private static class Item implements Comparable<Item> {
        String name;
        int weight;
        int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item other) {
            return this.name.compareTo(other.name);
        }
    }

    private static int[][] table;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());

        List<Item> items = new ArrayList<>();

        String line;
        while (!"end".equals(line = scanner.nextLine())) {
            String[] tokens = line.split("\\s+");

            Item item = new Item(
                    tokens[0],
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2])
            );

            items.add(item);
        }

        table = new int[items.size() + 1][capacity + 1];
        List<Item> takenItems = fillKnapsack(items, capacity);

        int bestValue = table[items.size()][capacity];
        int weight = capacity;
        while (table[items.size()][weight - 1] == bestValue) {
            weight--;
        }

        System.out.println("Total Weight: " + weight);
        System.out.println("Total Value: " + bestValue);
        takenItems
                .stream()
                .sorted(Item::compareTo)
                .map(item -> item.name)
                .forEach(System.out::println);
    }

    private static List<Item> fillKnapsack(List<Item> items, int capacity) {
        boolean[][] taken = new boolean[items.size() + 1][capacity + 1];

        for (int i = 1; i <= items.size(); i++) {

            Item item = items.get(i - 1);
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {

                int excluded = table[i - 1][currentCapacity];

                if (item.weight > currentCapacity) {
                    table[i][currentCapacity] = excluded;
                } else {
                    int included = item.value + table[i - 1][currentCapacity - item.weight];
                    if (included > excluded) {
                        table[i][currentCapacity] = included;
                        taken[i][currentCapacity] = true;
                    } else {
                        table[i][currentCapacity] = excluded;
                    }
                }
            }
        }

        List<Item> takenItems = new ArrayList<>();
        for (int i = items.size(); i > 0; i--) {
            if (taken[i][capacity]) {
                Item item = items.get(i - 1);
                takenItems.add(item);

                capacity -= item.weight;
            }
        }

        return takenItems;
    }
}
