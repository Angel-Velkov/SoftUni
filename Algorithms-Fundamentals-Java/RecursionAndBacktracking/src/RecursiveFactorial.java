import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {

        int num = new Scanner(System.in).nextInt();

        System.out.println(factorial(num));
    }

    private static long factorial(int number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
