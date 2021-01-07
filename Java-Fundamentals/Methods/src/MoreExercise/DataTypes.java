package MoreExercise;

import java.util.Scanner;

public class DataTypes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();
        String val = scanner.nextLine();
        switch (type) {
            case "int":
                int number = Integer.parseInt(val);
                System.out.println(multipleNumberBy2(number));
                break;
            case "real":
                double realNum = Double.parseDouble(val);
                System.out.printf("%.2f", multipleRealNumByOneAndAHalf(realNum));
                break;
            case "string":
                System.out.println(add$AroundTheString(val));
                break;
        }
    }

    private static String add$AroundTheString(String val) {
        String result = '$' + val + '$';
        return result;
    }

    private static double multipleRealNumByOneAndAHalf(double realNum) {
        double result = realNum * 1.5;
        return result;
    }

    private static int multipleNumberBy2(int number) {
        int result = number * 2;
        return result;
    }
}
