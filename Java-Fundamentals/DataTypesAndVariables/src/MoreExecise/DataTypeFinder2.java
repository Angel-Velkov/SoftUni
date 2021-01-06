package MoreExecise;

import java.util.Scanner;

public class DataTypeFinder2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while (!input.equals("END")) {
            StringBuilder type = new StringBuilder();
            String dataType;
            int pintCounter = 0;
            boolean isFloat = false;
            boolean isChar = false;
            boolean isInteger = false;
            boolean isBoolean = false;

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);

                if (currentChar == 46) {
                    pintCounter++;
                }

                if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                    isBoolean = true;

                } else if (input.length() < 2 && !(currentChar >= 48 && currentChar <= 57)) {
                    isChar = true;

                } else if (currentChar >= 48 && currentChar <= 57 || currentChar == 45 || currentChar == 46) {
                    type.append(currentChar);
                    isInteger = true;

                    if (currentChar == 46) {
                        isFloat = true;
                    }

                } else {
                    type.append(currentChar);
                }
                if (!(currentChar >= 48 && currentChar <= 57 || currentChar == 45 || currentChar == 46)) {
                    isFloat = false;
                    isInteger = false;
                    break;
                }

            }
            if (isChar) {
                dataType = "is character type";

            } else if (isInteger && pintCounter < 2) {

                if (isFloat) {
                    dataType = "is floating point type";

                } else {
                    dataType = "is integer type";
                }

            } else if (isBoolean) {
                dataType = "is boolean type";

            } else {
                dataType = "is string type";
            }

            System.out.println(input + " " + dataType);

            input = scan.nextLine();
        }
    }
}
