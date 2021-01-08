package Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SoftUniCoursePlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lessons = new ArrayList<>(Arrays.asList(scanner.nextLine().split(", ")));
        String input = scanner.nextLine();

        while (!input.equals("course start")) {
            String[] inputAsArray = input.split(":");
            String command = inputAsArray[0];
            String lessonTitle = inputAsArray[1];
            switch (command) {
                case "Add":
                    if (!lessons.contains(lessonTitle)) {
                        lessons.add(lessonTitle);
                    }
                    break;
                case "Insert":
                    if (!lessons.contains(lessonTitle)) {
                        int index = Integer.parseInt(inputAsArray[2]);
                        lessons.add(index, lessonTitle);
                    }
                    break;
                case "Remove":
                    removeLesson(lessons, lessonTitle);
                    break;
                case "Swap":
                    String lessonToSlap = inputAsArray[2];
                    if (lessons.contains(lessonTitle) && lessons.contains(lessonToSlap)) {
                        swapTwoStrings(lessons, lessonTitle, lessonToSlap);
                    }
                    break;
                case "Exercise":
                    if (!lessons.contains(lessonTitle)) {
                        lessons.add(lessonTitle);
                    }
                    if (!lessons.contains(lessonTitle + "-Exercise")) {
                        lessons.add(lessons.indexOf(lessonTitle) + 1, lessonTitle + "-Exercise");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (int i = 0; i < lessons.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, lessons.get(i));
        }
    }

    private static void removeLesson(List<String> lessons, String lessonTitle) {

        lessons.remove(lessonTitle);
        lessons.remove(lessonTitle + "-Exercise");
    }

    private static void swapTwoStrings(List<String> lessons, String firstLesson, String secondLesson) {
        int indexA = lessons.indexOf(firstLesson);
        int indexB = lessons.indexOf(secondLesson);

        lessons.set(indexA, secondLesson);
        lessons.set(indexB, firstLesson);

        if (indexB + 1 < lessons.size() && lessons.get(indexB + 1).equals(secondLesson + "-Exercise")) {
            lessons.remove(indexB + 1);
            indexB = lessons.indexOf(secondLesson);
            lessons.add(indexB + 1, secondLesson + "-Exercise");
        }

        if (indexA + 1 < lessons.size() && lessons.get(indexA + 1).equals(firstLesson + "-Exercise")) {
            lessons.remove(indexA + 1);
            indexA = lessons.indexOf(firstLesson);
            lessons.add(indexA + 1, firstLesson + "-Exercise");
        }
    }
}
