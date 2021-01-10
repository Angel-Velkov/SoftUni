package Exercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = ">>(?<name>[A-Za-z]+)<<(?<price>\\d+(\\.\\d+)?)!(?<quantity>\\d+)";
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);

        List<String> names = new ArrayList<>();

        double totalPrice = 0;

        while (!input.equals("Purchase")) {
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String productName = matcher.group("name");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));

                totalPrice += (price * quantity);
                names.add(productName);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bought furniture:");
        names.forEach(System.out::println);

        System.out.printf("Total money spend: %.2f", totalPrice);
    }
}
