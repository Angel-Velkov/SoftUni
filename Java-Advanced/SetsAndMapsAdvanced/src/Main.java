import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr1 = {2, 1, 3};
        int[] arr2 = {2, 3, 1};
        System.out.println(Arrays.equals(arr2, arr1));
    }
}
