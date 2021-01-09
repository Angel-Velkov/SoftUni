package Exercise;

import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> companyInformation = new TreeMap<>();

        String input;

        while (!"End".equals(input = scanner.nextLine())) {
            String[] data = input.split("->");

            String companyName = data[0];
            String ID = data[1];

            List<String> employeesIDs = companyInformation.get(companyName);
            if (employeesIDs == null) {
                employeesIDs = new ArrayList<>();
                companyInformation.put(companyName, employeesIDs);
            }

            if (!employeesIDs.contains(ID)) {
                employeesIDs.add(ID);
            }
        }

        companyInformation.entrySet()
                .forEach(c -> {
                    System.out.println(c.getKey());
                    c.getValue()
                            .forEach(id -> System.out.println("--" + id));
                });
    }
}
