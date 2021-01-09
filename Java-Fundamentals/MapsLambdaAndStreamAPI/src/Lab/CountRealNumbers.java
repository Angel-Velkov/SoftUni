package Lab;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountRealNumbers {
    public static void main(String[] args) {
        Map<Double, Integer> numbersOccurrences = new TreeMap<>();

        String[] line = new Scanner(System.in).nextLine().split(" ");
        for (int i = 0; i < line.length; i++) {

            double number = Double.parseDouble(line[i]);
            Integer occurrence = numbersOccurrences.get(number);

            if (occurrence == null) {
                occurrence = 0;
            }

            numbersOccurrences.put(number, occurrence + 1);
        }

        for (Map.Entry<Double, Integer> entry : numbersOccurrences.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.#######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
}
