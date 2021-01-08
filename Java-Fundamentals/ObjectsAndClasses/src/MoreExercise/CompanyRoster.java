package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyRoster {
    public static class Employee {
        String name;
        double salary;
        String position;
        String department;
        String email = "n/a";
        int age = -1;

        Employee(String name, double salary, String position, String department) {
            this.name = name;
            this.salary = salary;
            this.position = position;
            this.department = department;
        }

        public String getName() {
            return this.name;
        }

        public double getSalary() {
            return this.salary;
        }

        public String getEmail() {
            return this.email;
        }

        public int getAge() {
            return this.age;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    public static class Department {
        String departmentName;
        List<Employee> employees;

        Department(String departmentName) {
            this.departmentName = departmentName;
            this.employees = new ArrayList<>();
        }

        public String getDepartmentName() {
            return this.departmentName;
        }

        public List<Employee> getEmployees() {
            return this.employees;
        }

        public void addEmployee(Employee employee) {
            this.employees.add(employee);
        }


        public double calculateAverageSalary() {
            double averageSalary = 0;
            for (Employee employee : this.employees) {
                averageSalary += employee.getSalary();
            }
            averageSalary /= employees.size();

            return averageSalary;
        }

        public void sortEmployeesBySalary() {
            employees.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        }
    }

    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] employeeData = scanner.nextLine().split("\\s+");
            String name = employeeData[0];
            double salary = Double.parseDouble(employeeData[1]);
            String position = employeeData[2];
            String departmentName = employeeData[3];

            Employee employee = new Employee(name, salary, position, departmentName);

            switch (employeeData.length) {
                case 5:
                    if (employeeData[4].contains("@")) {
                        employee.setEmail(employeeData[4]);
                    } else {
                        employee.setAge(Integer.parseInt(employeeData[4]));
                    }
                    break;
                case 6:
                    if (employeeData[4].contains("@")) {
                        employee.setEmail(employeeData[4]);
                        employee.setAge(Integer.parseInt(employeeData[5]));
                    } else {
                        employee.setAge(Integer.parseInt(employeeData[4]));
                        employee.setEmail(employeeData[5]);
                    }
                    break;
            }

            boolean search = false;
            Department dep = null;
            for (Department department : departments) {
                if (department.getDepartmentName().equals(departmentName)) {
                    dep = department;
                    search = true;
                    break;
                }
            }

            if (!search) {
                dep = new Department(departmentName);
                departments.add(dep);
            }
            dep.addEmployee(employee);
        }

        double highestAverageSalary = 0;
        Department dep = null;
        for (Department department : departments) {
            if (department.calculateAverageSalary() > highestAverageSalary) {
                highestAverageSalary = department.calculateAverageSalary();
                dep = department;
            }
        }

        assert dep != null;
        System.out.println("Highest Average Salary: " + dep.getDepartmentName());
        dep.sortEmployeesBySalary();
        for (Employee employee : dep.getEmployees()) {
            System.out.printf("%s %.2f %s %d%n"
                    , employee.getName(), employee.getSalary(), employee.getEmail(), employee.getAge());
        }
    }
}
