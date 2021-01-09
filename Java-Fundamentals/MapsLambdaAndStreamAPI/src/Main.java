import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> example = new HashMap<>();

        String name = scanner.next();

        while (!name.equals("end")) {
            if (name.equals("report")) {
                String searchingCity = scanner.next();

                if (example.get(searchingCity) == null) {
                    System.out.println("This country is not in the Map");
                } else {
                    System.out.println(example.get(searchingCity));
                }
            } else {
                int newlyInfected = scanner.nextInt();

                example.putIfAbsent(name, 0);
                int age = example.get(name);


                example.put(name, age + newlyInfected);
            }
            name = scanner.next();
        }

        for (Map.Entry<String, Integer> entry : example.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
