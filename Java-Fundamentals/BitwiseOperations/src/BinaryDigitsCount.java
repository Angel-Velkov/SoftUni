import java.util.Scanner;

public class BinaryDigitsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int b = scanner.nextInt();

        //String binaryDigits = Integer.toBinaryString(n);

        String binaryDigits = getBinaryDigits(n);

        int binaryDigitCounter = 0;
        for (int i = 0; i < binaryDigits.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(binaryDigits.charAt(i)));

            if (b == digit) {
                binaryDigitCounter++;
            }
        }

        System.out.printf("We have %d %s.",
                binaryDigitCounter,
                b == 0
                        ? "zeroes"
                        : "ones");
    }

    private static String getBinaryDigits(int num) {
        StringBuilder binaryDigits = new StringBuilder();

        while (num > 0) {
            int b = num % 2;
            binaryDigits.append(b);
            num /= 2;
        }
        return binaryDigits.reverse().toString();
    }
}
