package TrialAugust2020;

import java.util.Scanner;

public class PCStore {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double processorPrice = Double.parseDouble(scan.nextLine());
        double videoCardPrice = Double.parseDouble(scan.nextLine());
        double ramPrice = Double.parseDouble(scan.nextLine());
        int ramCount = Integer.parseInt(scan.nextLine());
        double discount = Double.parseDouble(scan.nextLine());

        processorPrice = processorPrice - processorPrice * discount;
        videoCardPrice = videoCardPrice - videoCardPrice * discount;
        double sum = (processorPrice + videoCardPrice + (ramCount * ramPrice)) * 1.57;

        System.out.printf("Money needed - %.2f leva.", sum);
    }
}
