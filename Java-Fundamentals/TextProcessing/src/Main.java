import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        String text = scanner.nextLine();

        String result = text;
        int nextWordIndex = result.indexOf(word);

        while (nextWordIndex != -1) {
            int currentIndex = 0;
            List<String> substrings = new ArrayList<>();

            while (nextWordIndex != -1) {
                String substr = result.substring(currentIndex, nextWordIndex);
                substrings.add(substr);
                currentIndex = nextWordIndex + word.length();
                nextWordIndex = result.indexOf(word, currentIndex);
            }
            substrings.add(result.substring(currentIndex));
            result = String.join("", substrings);
            nextWordIndex = result.indexOf(word, 0);
        }
        System.out.println(result);
    }
}
