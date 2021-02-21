import java.util.Scanner;

public class Generating01Vectors {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();
        Byte[] memory = new Byte[n];

        generateVector(memory, 0);
    }

    private static void generateVector(Byte[] memory, int index) {
        if (index >= memory.length) {
            printVector(memory);
            return;
        }

        for (byte i = 0; i <=1; i++) {
            memory[index] = i;
            generateVector(memory, index + 1);
        }
    }

    private static void printVector(Byte[] memory) {
        for (Byte b : memory) {
            System.out.print(b);
        }
        System.out.println();
    }
}
