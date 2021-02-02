package Exercises.CompanyRoster;

import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Employee>> departmentsEmployees = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String name = data[0];
            double salary = Double.parseDouble(data[1]);
            String position = data[2];
            String department = data[3];

            Employee employee = readEmployee(data, name, salary, position, department);

            departmentsEmployees.putIfAbsent(department, new ArrayList<>());
            departmentsEmployees.get(department).add(employee);
        }

        Function<List<Employee>, Double> averageSalary = employees ->
                employees
                        .stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0);

        if (!departmentsEmployees.isEmpty()) {
            Map.Entry<String, List<Employee>> bestDepartment = bestAverageSalary(averageSalary, departmentsEmployees);

            System.out.println("Highest Average Salary: " + bestDepartment);
            departmentsEmployees.get(bestDepartment.getKey())
                    .stream()
                    .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                    .forEach(e -> System.out.printf("%s %.2f %s %d%n", e.getName(), e.getSalary(), e.getEmail(), e.getAge()));
        }
    }

    private static Map.Entry<String, List<Employee>> bestAverageSalary
            (Function<List<Employee>, Double> averageSalary, Map<String, List<Employee>> departmentsEmployees) {
        return departmentsEmployees
                .entrySet()
                .stream()
                .max((d1, d2) -> {
                    double first = averageSalary.apply(d1.getValue());
                    double second = averageSalary.apply(d2.getValue());

                    return Double.compare(second, first);
                }).get();
    }


    private static Employee readEmployee(String[] data, String name, double salary, String position, String department) {
        Employee employee;
        switch (data.length) {
            case 5: {
                if (isANumber(data[4])) {
                    int age = Integer.parseInt(data[4]);
                    employee = new Employee(name, salary, position, department, age);
                } else {
                    String email = data[4];
                    employee = new Employee(name, salary, position, department, email);
                }
            }
            break;
            case 6: {
                int age;
                String email;
                if (isANumber(data[4])) {
                    age = Integer.parseInt(data[4]);
                    email = data[5];
                } else {
                    email = data[4];
                    age = Integer.parseInt(data[5]);
                }
                employee = new Employee(name, salary, position, department, email, age);
            }
            break;
            default: {
                employee = new Employee(name, salary, position, department);
            }
        }
        return employee;
    }

    public static boolean isANumber(String line) {
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }
}
