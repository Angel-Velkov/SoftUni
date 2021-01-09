package Exercise;

import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> courses = new LinkedHashMap<>();

        String line = scanner.nextLine();

        while (!line.equals("end")) {
            String[] register = line.split("\\s*:\\s*");

            String courseName = register[0];
            String studentName = register[1];

            List<String> students = courses.get(courseName);

            if (!courses.containsKey(courseName)) {
                students = new ArrayList<>();
                courses.put(courseName, students);
            }
            students.add(studentName);

            line = scanner.nextLine();
        }
        courses.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().size()- a.getValue().size())
                .forEach(e -> {
                    System.out.println(e.getKey() + ": " + e.getValue().size());
                    e.getValue()
                            .stream()
                            .sorted()
                            .forEach(s -> System.out.println("-- " + s));
                });
    }
}
