import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfTwoBigIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();
        String b = scanner.nextLine();

        List<Byte> sum = sumBigIntegerNumbers(a, b);
        for (int i = sum.size() - 1; i >= 0; i--) {
            System.out.print(sum.get(i));
        }
        System.out.println();
        System.out.println("-------------------------------------");

        BigInteger aNum = new BigInteger(a);
        BigInteger bNum = new BigInteger(b);
        aNum = aNum.add(bNum);
        System.out.println(aNum);
    }

    private static List<Byte> sumBigIntegerNumbers(String a, String b) {
        List<Byte> firstNum = parseLineInReversedByteList(a);
        List<Byte> secondNum = parseLineInReversedByteList(b);

        byte nextNum = 0;
        for (int i = 0; i < firstNum.size(); i++) {
            if (i < secondNum.size()) {
                if (i == firstNum.size() - 1 && i != secondNum.size() - 1) {
                    firstNum.add((byte) 0);
                }
                byte digitA = firstNum.get(i);
                byte digitB = secondNum.get(i);
                byte currentNum = (byte) ((nextNum + digitA + digitB) % 10);
                firstNum.set(i, currentNum);
                nextNum = (byte) ((digitA + digitB + nextNum) / 10);
            } else {
                firstNum.set(i, (byte) (firstNum.get(i) + nextNum));
                nextNum = 0;
            }
        }
        if (nextNum == 1) {
            firstNum.add((byte) 1);
        }
        return firstNum;
    }

    private static List<Byte> parseLineInReversedByteList(String line) {
        List<Byte> numbers = new ArrayList<>();
        for (int i = line.length() - 1; i >= 0; i--) {
            byte currentNum = Byte.parseByte(line.substring(i, i + 1));
            numbers.add(currentNum);
        }
        return numbers;
    }
}