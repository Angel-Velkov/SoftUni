package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Articles2 {

    static class Article {

        String title;
        String content;
        String author;

        Article(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        String getTitle() {
            return title;
        }

        String getContent() {
            return content;
        }

        String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return String.format("%s - %s: %s", this.title, this.content, this.author);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Article> articlesList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(", ");

            String title = data[0];
            String content = data[1];
            String author = data[2];

            Article article = new Article(title, content, author);

            articlesList.add(article);
        }

        String command = scanner.nextLine();

        sortTheListAndPrintIt(articlesList, command);

    }

    private static void sortTheListAndPrintIt(List<Article> articlesList, String command) {
        switch (command) {

            case "title":
                articlesList
                        .stream()
                        .sorted((t1, t2) -> t1.getTitle().compareTo(t2.getTitle()))
                        .forEach(t -> System.out.println(t.toString()));
                break;

            case "content":
                articlesList
                        .stream()
                        .sorted((c1, c2) -> c1.getContent().compareTo(c2.getContent()))
                        .forEach(c -> System.out.println(c.toString()));
                break;

            case "author":
                articlesList
                        .stream()
                        .sorted((a1, a2) -> a1.getAuthor().compareTo(a2.getAuthor()))
                        .forEach(a -> System.out.println(a.toString()));
                break;
        }
    }
}
