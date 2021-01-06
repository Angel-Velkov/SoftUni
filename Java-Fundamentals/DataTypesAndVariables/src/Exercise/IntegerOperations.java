package Exercise;

import java.util.Scanner;

public class IntegerOperations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int firstNum = scan.nextInt();
        int secondNum = scan.nextInt();
        int thirdNum = scan.nextInt();
        int fourthNum = scan.nextInt();

        System.out.println((firstNum + secondNum) / thirdNum * fourthNum);
    }
}
