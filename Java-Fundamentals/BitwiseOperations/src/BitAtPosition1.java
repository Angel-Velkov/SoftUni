import java.util.Scanner;

public class BitAtPosition1 {
    public static void main(String[] args) {

        int num = new Scanner(System.in).nextInt();
        int p = 1;

        int digit = (num >> p) & 1;
        System.out.println(digit);
    }
}
