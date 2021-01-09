package Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> webSide = new TreeMap<>();

        String input;

        while (!"Lumpawaroo".equals(input = scanner.nextLine())) {
            String[] command;
            String operand;

            if (input.contains("|")) {
                command = input.split("\\s+\\|\\s+");
                operand = "|";
            } else {
                command = input.split("\\s+->\\s+");
                operand = "->";
            }

            String name;
            String side;

            switch (operand) {
                case "|":
                    side = command[0];
                    name = command[1];

                    if (!webSide.containsKey(side)) {
                        webSide.put(side, new ArrayList<>());
                    }

                    boolean existingUser = false;

                    for (List<String> list : webSide.values()) {
                        if (list.contains(name)) {
                            existingUser = true;
                            break;
                        }
                    }

                    if (!existingUser) {
                        webSide.get(side).add(name);
                    }

                    break;
                case "->":
                    name = command[0];
                    side = command[1];

                    if (!webSide.containsKey(side)) {
                        webSide.put(side, new ArrayList<>());
                    }

                    for (Map.Entry<String, List<String>> current : webSide.entrySet()) {
                        if (current.getValue().contains(name)) {
                            current.getValue().remove(name);
                            break;
                        }
                    }

                    webSide.get(side).add(name);
                    System.out.printf("%s joins the %s side!%n", name, side);

                    break;
                default:
                    break;
            }
        }

        webSide
                .entrySet()
                .stream()
                .filter(l -> l.getValue().size() > 0)
                .sorted(Map.Entry.<String, List<String>>comparingByValue
                        (Comparator.comparing(l -> l.size())).reversed())
                .forEach(s -> {
                    System.out.printf("Side: %s, Members: %d%n"
                            , s.getKey()
                            , s.getValue().size());

                    s.getValue()
                            .stream()
                            .sorted((a, b) -> a.compareTo(b))
                            .forEach(p -> System.out.println("! " + p));
                });
    }
}  
