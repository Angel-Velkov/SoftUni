package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students {
    static class Student {

        String firstName;

        String lastName;
        int age;
        String hometown;

        Student(String firstName, String lastName, int age, String hometown) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.hometown = hometown;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public String getHometown() {
            return hometown;
        }


        //----------------------------
        public void setAge(int age) {
            this.age = age;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        @Override
        public String toString() {
            return String.format("%s %s is %d years old",
                    this.getFirstName(), this.getLastName(), this.getAge());
        }

        public static Student parseStudent(String line) {
            String[] parameters = line.split("\\s+");

            String firstName = parameters[0];
            String lastName = parameters[1];
            int age = Integer.parseInt(parameters[2]);
            String hometown = parameters[3];

            return new Student(firstName, lastName, age, hometown);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();

        String line = scanner.nextLine();

        while (!line.equals("end")) {

            Student student = Student.parseStudent(line);

            Student existingStudent = getStudent(students, student.getFirstName(), student.getLastName());
            if (existingStudent != null) {
                existingStudent.setAge(student.getAge());
                existingStudent.setHometown(student.getHometown());
            } else {
                students.add(student);
            }

            line = scanner.nextLine();
        }

        String searchTownName = scanner.nextLine();
        for (Student student : students) {
            if (student.getHometown().equals(searchTownName)) {
                System.out.println(student);
            }
        }
    }

    private static Student getStudent(List<Student> students, String firstName, String lastName) {
        Student existingStudent = null;

        for (Student student : students) {
            if (firstName.equals(student.getFirstName()) && lastName.equals(student.getLastName())) {
                existingStudent = student;
            }
        }
        return existingStudent;
    }
}
