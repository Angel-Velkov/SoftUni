package Exercise;

import java.util.Scanner;

public class USDToBGN {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double USD = Double.parseDouble(scan.nextLine());
        double BGN = USD * 1.79549;
        System.out.println(BGN);
    }
}
