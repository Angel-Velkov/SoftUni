import java.util.Scanner;

public class RecursiveDrawing {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        printParallelRightAngledTriangles(n);
    }

    private static void printParallelRightAngledTriangles(int n) {
        if (n <= 0) {
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.print('*');
        }
        System.out.println();

        printParallelRightAngledTriangles(n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print('#');
        }
        System.out.println();
    }
}
