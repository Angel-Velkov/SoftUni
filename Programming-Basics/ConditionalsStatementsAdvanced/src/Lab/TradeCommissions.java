package Lab;

import java.util.Scanner;

public class TradeCommissions {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String city = scan.nextLine();
        double sells = Double.parseDouble(scan.nextLine());
        double commission = 0;
        if (city.equals("Sofia")) {
            if (sells >= 0 && sells <= 500) {
                commission = sells * 0.05;
            } else if (sells > 500 && sells <= 1000) {
                commission = sells * 0.07;
            } else if (sells > 1000 && sells <= 10000) {
                commission = sells * 0.08;
            } else if (sells > 10000) {
                commission = sells * 0.12;
            }
        } else if (city.equals("Varna")) {
            if (sells >= 0 && sells <= 500) {
                commission = sells * 0.045;
            } else if (sells > 500 && sells <= 1000) {
                commission = sells * 0.075;
            } else if (sells > 1000 && sells <= 10000) {
                commission = sells * 0.1;
            } else if (sells > 10000) {
                commission = sells * 0.13;
            }
        } else if (city.equals("Plovdiv")) {
            if (sells >= 0 && sells <= 500) {
                commission = sells * 0.055;
            } else if (sells > 500 && sells <= 1000) {
                commission = sells * 0.08;
            } else if (sells > 1000 && sells <= 10000) {
                commission = sells * 0.12;
            } else if (sells > 10000) {
                commission = sells * 0.145;
            }
        }
        if (sells >= 0 && (city.equals("Sofia") || city.equals("Varna") || city.equals("Plovdiv"))) {
            System.out.printf("%.02f", commission);

        } else {
            System.out.println("error");
        }
    }
}
