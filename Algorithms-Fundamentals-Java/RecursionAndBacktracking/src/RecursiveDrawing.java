import java.util.Scanner;

public class RecursiveDrawing {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        printFigure(n);
    }

    private static void printFigure(int n) {
        if (n <= 0) {
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.print('*');
        }
        System.out.println();

        printFigure(n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print('#');
        }
        System.out.println();
    }
}
