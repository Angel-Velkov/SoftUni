package Exercise;

import java.util.Scanner;

public class OperationsBetweenNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = Integer.parseInt(scan.nextLine());
        int b = Integer.parseInt(scan.nextLine());
        String sing = scan.nextLine();

        double result = 0;

        switch (sing) {
            case "*":
                result = a * b;
                break;
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "%":
                if (b == 0) {
                    System.out.printf("Cannot divide %d by zero", a);
                } else {
                    result = a % b;
                    System.out.printf("%d %% %d = %.0f", a, b, result);
                }
                break;
            case "/":
                if (b == 0) {
                    System.out.printf("Cannot divide %d by zero", a);
                } else {
                    result = (1.0 * a / b);
                    System.out.printf("%d / %d = %.2f", a, b, result);
                }
                break;
        }
        if (!sing.equals("%") && !sing.equals("/")) {
            String type = "";
            if (result % 2 == 0) {
                type = "even";
            } else {
                type = "odd";
            }
            System.out.printf("%d %s %d = %.0f - %s", a, sing, b, result, type);
        }
    }
}
