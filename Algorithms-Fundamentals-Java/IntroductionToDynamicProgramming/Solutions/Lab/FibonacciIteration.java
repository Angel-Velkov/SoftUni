import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciIteration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        BigInteger current = new BigInteger("1");
        BigInteger previous = new BigInteger("1");

        for (int i = 3; i <= number; i++) {
            BigInteger temp = current;

            current = previous.add(current);

            previous = temp;
        }

        System.out.println(current);
    }
}
