package MoreExercise;

import java.util.Scanner;

public class EnglishNameOfTheLastDigit {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int number = scan.nextInt();
        int lastNumber = number % 10;
        String numberName = "";

        switch (lastNumber) {
            case 9:
                numberName = "nine";
                break;
            case 8:
                numberName = "eight";
                break;
            case 7:
                numberName = "seven";
                break;
            case 6:
                numberName = "six";
                break;
            case 5:
                numberName = "five";
                break;
            case 4:
                numberName = "four";
                break;
            case 3:
                numberName = "three";
                break;
            case 2:
                numberName = "two";
                break;
            case 1:
                numberName = "one";
                break;
            case 0:
                numberName = "zero";
        }
        System.out.println(numberName);
    }
}
