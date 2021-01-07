package Lab;

import java.util.Scanner;

public class MathOperations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = Integer.parseInt(scan.nextLine());
        char operation = scan.nextLine().charAt(0);
        int b = Integer.parseInt(scan.nextLine());

        if (calculate(a, operation, b) == (long) calculate(a, operation, b)) {
            System.out.printf("%.0f", calculate(a, operation, b));
        } else {
            System.out.println(calculate(a, operation, b));
        }
    }

    private static double calculate(int a, char operation, int b) {
        double result = 0;
        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '*':
                result = a * b;
                break;
            case '-':
                result = a - b;
                break;
            case '/':
                result = (double) a / b;
        }
        return result;
    }
}
