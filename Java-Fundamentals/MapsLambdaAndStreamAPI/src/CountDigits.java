import java.util.Scanner;

public class CountDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = scanner.nextLine().split("\\s+");
        int[] counter = new int[10];

        for (String s : line) {
            int currentNum = Integer.parseInt(s);
            counter[currentNum]++;
        }

        for (int i = 0; i < counter.length; i++) {
            if (counter[i] != 0){
                System.out.println(i + " -> " + counter[i]);
            }
        }
    }
}
