package Exercise;

import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        byte first = Byte.parseByte(scan.nextLine());
        byte second = Byte.parseByte(scan.nextLine());

        double result = (double) calculateFactorial(first) / calculateFactorial(second);
        System.out.printf("%.2f", result);
    }

    static long calculateFactorial(byte num) {
        long factorial = 1;
        for (byte i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
