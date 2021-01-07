package Lab;

import java.util.Scanner;

public class Calculations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String operations = scan.nextLine();
        int firstNum = Integer.parseInt(scan.nextLine());
        int secondNum = Integer.parseInt(scan.nextLine());

        switch (operations) {
            case "add":
                add(firstNum, secondNum);
                break;
            case "multiply":
                multiply(firstNum, secondNum);
                break;
            case "subtract":
                subtract(firstNum, secondNum);
                break;
            case "divide":
                divide(firstNum, secondNum);
        }
    }

    static void add(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
    }

    static void multiply(int a, int b) {
        int sum = a * b;
        System.out.println(sum);
    }

    static void subtract(int a, int b) {
        int sum = a - b;
        System.out.println(sum);
    }

    static void divide(int a, int b) {
        int sum = a / b;
        System.out.println(sum);
    }
}
