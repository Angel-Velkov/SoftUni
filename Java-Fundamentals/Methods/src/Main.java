import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        BigDecimal number = new BigDecimal(scan.nextLine());
        int powNumber = Integer.parseInt(scan.nextLine());

        BigDecimal result = number.pow(powNumber);
        System.out.println(result);
    }
}
