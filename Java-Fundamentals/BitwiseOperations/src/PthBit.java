import java.util.Scanner;

public class PthBit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();

        int digit = (n >> p) & 1;
        System.out.println(digit);
    }
}
