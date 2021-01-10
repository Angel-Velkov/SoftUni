package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTML {
    public static void main(String[] args) {
        List<String> commentsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String title = scanner.nextLine();
        String content = scanner.nextLine();
        String comment;
        while (!"end of comments".equals(comment = scanner.nextLine())) {
            commentsList.add(comment);
        }
        printHTML(title, "h1");
        printHTML(content, "article");
        for (String s : commentsList) {
            printHTML(s, "div");
        }
    }

    private static void printHTML(String text, String type) {
        System.out.println("<" + type + ">");
        System.out.println("    " + text);
        System.out.println("</" + type + ">");
    }
}
