package Exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> productPrice = new LinkedHashMap<>();
        Map<String, Integer> productQuantity = new HashMap<>();

        String input;

        while (!"buy".equals(input = scanner.nextLine())) {
            String[] productData = input.split("\\s+");

            String name = productData[0];
            double newPrice = Double.parseDouble(productData[1]);
            int quantity = Integer.parseInt(productData[2]);


            Double currentPrice = productPrice.get(name);
            if (currentPrice == null) {
                currentPrice = 0.0;
            }

            if (currentPrice != newPrice) {
                productPrice.put(name, newPrice);
            }

            Integer totalQuantity = productQuantity.get(name);

            if (totalQuantity == null) {
                totalQuantity = 0;
            }
            productQuantity.put(name, quantity + totalQuantity);
        }

        for (Map.Entry<String, Double> entry : productPrice.entrySet()) {
            System.out.printf("%s -> %.2f%n"
                    , entry.getKey()
                    , entry.getValue() * productQuantity.get(entry.getKey()));
        }
    }
}
