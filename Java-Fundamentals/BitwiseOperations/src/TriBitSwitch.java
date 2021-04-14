import java.util.Scanner;

public class TriBitSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();

        int mask = (7 << p);

        int result = n ^ mask;
        System.out.println(result);
    }
}
