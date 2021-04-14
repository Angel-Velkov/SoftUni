import java.util.Arrays;
import java.util.Scanner;

public class OddTimes {
    public static void main(String[] args) {

        int[] arr = Arrays.stream(new Scanner(System.in).nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            result = result ^ arr[i];
        }

        System.out.println(result);
    }
}
