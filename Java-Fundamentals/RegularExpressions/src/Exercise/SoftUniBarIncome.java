package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftUniBarIncome {
    private static class Customer {
        String name;
        String product;
        double totalPrice;

        Customer(String name, String product, double totalPrice) {
            this.name = name;
            this.product = product;
            this.totalPrice = totalPrice;
        }

        public String getName() {
            return this.name;
        }

        public String getProduct() {
            return this.product;
        }

        public double getTotalPrice() {
            return this.totalPrice;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "%(?<customerName>[A-Z][a-z]+)%[^|$%.]*?<(?<product>\\w+)>[^|$%.]*?\\|(?<count>\\d+)\\|[^|$%.]*?(?<price>\\d+(\\.\\d+)?)\\$";
        Pattern pattern = Pattern.compile(regex);

        String input;
        List<Customer> customers = new ArrayList<>();
        double totalIncome = 0;
        while (!"end of shift".equals(input = scanner.nextLine())) {
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String customerName = matcher.group("customerName");
                String product = matcher.group("product");
                int count = Integer.parseInt(matcher.group("count"));
                double price = Double.parseDouble(matcher.group("price"));

                double totalPrice = price * count;

                Customer customer = new Customer(customerName, product, totalPrice);
                customers.add(customer);

                totalIncome += totalPrice;
            }
        }

        for (Customer c : customers) {
            System.out.printf("%s: %s - %.2f%n"
                    , c.getName(), c.getProduct(), c.getTotalPrice());
        }
        System.out.printf("Total income: %.2f%n", totalIncome);
    }
}
