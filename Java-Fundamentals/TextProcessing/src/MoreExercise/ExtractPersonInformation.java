package MoreExercise;

import java.util.Scanner;

public class ExtractPersonInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            int beginningOfTheName = line.indexOf("@") + 1;
            int endingOfTheName = line.indexOf("|");
            String name;
            if (beginningOfTheName != -1 && beginningOfTheName < endingOfTheName && endingOfTheName != -1) {
                name = line.substring(beginningOfTheName, endingOfTheName);
            } else {
                name = "{No name}";
            }

            int beginningOfTheAge = line.indexOf("#") + 1;
            int endingOfTheAge = line.indexOf("*");
            String age;
            if (beginningOfTheAge != -1 && beginningOfTheAge < endingOfTheAge && endingOfTheAge != -1) {
                age = line.substring(beginningOfTheAge, endingOfTheAge);
            } else {
                age = "{No age}";
            }

            System.out.println(name + " is " + age + " years old.");
        }
    }
}
