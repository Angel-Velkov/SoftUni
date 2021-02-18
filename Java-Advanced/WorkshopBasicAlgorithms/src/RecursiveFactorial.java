import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {

        int num = new Scanner(System.in).nextInt();

        int factorial = factorial(num);
        System.out.println(factorial);
    }

    private static int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
