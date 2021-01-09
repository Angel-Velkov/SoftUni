package Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> keyMaterials = new HashMap<>();
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        Map<String, Integer> junkMaterials = new TreeMap<>();

        boolean foundLegendaryItem = false;

        while (!foundLegendaryItem) {
            String[] input = scanner.nextLine().split("\\s+");

            for (int i = 0; i < input.length; i += 2) {

                int currentQuantity = Integer.parseInt(input[i]);
                String material = input[i + 1].toLowerCase();

                if (material.equals("shards") || material.equals("fragments") || material.equals("motes")) {

                    int totalQuantity = keyMaterials.get(material);
                    keyMaterials.put(material, currentQuantity + totalQuantity);

                    if (keyMaterials.get(material) >= 250) {
                        keyMaterials.put(material, keyMaterials.get(material) - 250);
                        switch (material) {
                            case "shards":
                                System.out.println("Shadowmourne obtained!");
                                break;
                            case "fragments":
                                System.out.println("Valanyr obtained!");
                                break;
                            case "motes":
                                System.out.println("Dragonwrath obtained!");
                                break;
                        }
                        foundLegendaryItem = true;
                        break;

                    }
                } else {
                    Integer totalJunkQuantity = junkMaterials.get(material);

                    if (totalJunkQuantity == null) {
                        totalJunkQuantity = 0;
                    }

                    junkMaterials.put(material, totalJunkQuantity + currentQuantity);
                }
            }
        }
        keyMaterials.entrySet().stream()
                .sorted((a, b) -> {
                    int result = Integer.compare(b.getValue(), a.getValue());

                    if (result == 0) {
                        result = a.getKey().compareTo(b.getKey());
                    }
                    return result;
                })
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        junkMaterials.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
