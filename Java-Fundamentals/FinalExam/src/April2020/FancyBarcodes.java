package April2020;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String barcode = matcher.group("barcode");
                StringBuilder productGroup = new StringBuilder();
                for (int j = 0; j < barcode.length(); j++) {
                    char symbol = barcode.charAt(j);
                    if (Character.isDigit(symbol)) {
                        productGroup.append(symbol);
                    }
                }
                System.out.print("Product group: ");
                if (productGroup.length() == 0) {
                    System.out.println("00");
                } else {
                    System.out.println(productGroup);
                }
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
