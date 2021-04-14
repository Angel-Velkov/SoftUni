import java.util.Scanner;

public class BitDestroyer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();

        int mask = ~(1 << p);

        int result = n & mask;
        System.out.println(result);
    }
}
