package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderByAge {

    static class PersonData {
        String name;
        String ID;
        int age;

        PersonData(String name, String ID, int age) {
            this.name = name;
            this.ID = ID;
            this.age = age;
        }

        int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return String.format("%s with ID: %s is %d years old."
                    , this.name, this.ID, this.age);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line;

        List<PersonData> peoples = new ArrayList<>();

        while (!"End".equals(line = scanner.nextLine())) {

            String[] data = line.split("\\s+");
            String name = data[0];
            String ID = data[1];
            int age = Integer.parseInt(data[2]);

            PersonData person = new PersonData(name, ID, age);
            peoples.add(person);
        }

        peoples
                .stream()
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .forEach(p -> System.out.println(p));
    }
}
