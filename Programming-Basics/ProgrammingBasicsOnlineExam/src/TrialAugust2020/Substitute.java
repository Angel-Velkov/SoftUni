package TrialAugust2020;

import java.util.Scanner;

public class Substitute {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //•	K - началото на интервала за първото число от първия номер – цифра в интервала [0..8]
        //•	L - началото на интервала за второто число от първия номер – цифра в интервала [9..0]
        //•	M - началото на интервала за първото число от втория номер – цифра в интервала [0..8]
        //•	N - началото на интервала за второто число от втория номер – цифра в интервала [9..0]

        int K = Integer.parseInt(scan.nextLine());
        int L = Integer.parseInt(scan.nextLine());
        int M = Integer.parseInt(scan.nextLine());
        int N = Integer.parseInt(scan.nextLine());
        boolean flag = false;
        int counter = 0;

        for (int i = K; i <= 8; i++) {
            for (int j = 9; j >= L; j--) {
                for (int k = M; k <= 8; k++) {
                    for (int l = 9; l >= N; l--) {
                        boolean isPossible = i % 2 == 0 && j % 2 != 0 && k % 2 == 0 && l % 2 != 0;

                        int firstNum = i * 10 + j;
                        int secondNum = k * 10 + l;

                        if (isPossible && firstNum == secondNum) {
                            System.out.println("Cannot change the same player.");
                        } else if (isPossible) {
                            System.out.printf("%d - %d%n", firstNum, secondNum);
                            counter++;
                        }
                        if (counter == 6) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
