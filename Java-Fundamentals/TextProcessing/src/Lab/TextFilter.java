package Lab;

import java.util.Scanner;

public class TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bannedWords = scanner.nextLine().split(",\\s+");
        String text = scanner.nextLine();

        for (String currentBan : bannedWords) {
            text = text.replace(currentBan, repeat('*', currentBan.length()));
        }
        System.out.println(text);
    }

    private static String repeat(char symbol, int size) {
        StringBuilder repeatedSymbol = new StringBuilder();
        for (int i = 0; i < size; i++) {
            repeatedSymbol.append(symbol);
        }
        return repeatedSymbol.toString();
    }
}
