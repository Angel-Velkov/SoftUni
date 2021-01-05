package Exercise;

import java.util.Scanner;

public class MetricConverter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double num = Double.parseDouble(scan.nextLine());
        String inputMetric = scan.nextLine();
        String outputMetric = scan.nextLine();

        if (inputMetric.equals("cm")) {
            num = num / 100;
        } else if (inputMetric.equals("mm")) {
            num = num / 1000;
        }
        if (outputMetric.equals("cm")) {
            num = num * 100;
        } else if (outputMetric.equals("mm")) {
            num = num * 1000;
        }
        System.out.printf("%.3f", num);
    }
}
