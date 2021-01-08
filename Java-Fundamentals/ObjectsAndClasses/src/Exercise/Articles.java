package Exercise;

import java.util.Scanner;

public class Articles {

    static class Article {
        String title;
        String content;
        String author;

        Article(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getAuthor() {
            return author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String toString() {
            return String.format("%s - %s: %s", getTitle(), getContent(), getAuthor());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("," + "\\s+");
        Article article = new Article(input[0], input[1], input[2]);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(":" + "\\s+");
            String command = line[0];

            switch (command) {
                case "Edit":
                    String newContent = line[1];
                    article.setContent(newContent);
                    break;
                case "ChangeAuthor":
                    String newAuthor = line[1];
                    article.setAuthor(newAuthor);
                    break;
                case "Rename":
                    String newTitle = line[1];
                    article.setTitle(newTitle);
                    break;
            }
        }

        System.out.print(article.toString());
    }
}
