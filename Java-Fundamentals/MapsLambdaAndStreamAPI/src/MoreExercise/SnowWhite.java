package MoreExercise;

import java.util.*;

public class SnowWhite {
    private static class Dwarf {
        String name;
        String color;
        int physics;

        Dwarf(String name, String color, int physics) {
            this.name = name;
            this.color = color;
            this.physics = physics;
        }

        String getName() {
            return this.name;
        }

        String getColor() {
            return this.color;
        }

        int getPhysics() {
            return this.physics;
        }

        public void setPhysics(int physics) {
            this.physics = physics;
        }
    }

    public static void main(String[] args) {
        List<Dwarf> dwarfs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String input;
        Date start = new Date();
        while (!"Once upon a time".equals(input = scanner.nextLine())) {
            String[] dwarfsData = input.split(" <:> ");
            String name = dwarfsData[0];
            String color = dwarfsData[1];
            int physics = Integer.parseInt(dwarfsData[2]);

            Dwarf d = new Dwarf(name, color, physics);
            boolean flag = true;
            for (Dwarf dwarf : dwarfs) {
                if (dwarf.getName().equals(name)) {
                    if (dwarf.getColor().equals(color)) {
                        dwarf.setPhysics(Math.max(dwarf.getPhysics(), physics));
                    } else {
                        dwarfs.add(d);
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                dwarfs.add(d);
            }
        }

        Map<String, Integer> countOfDwarfsWithTheSameColor = new HashMap<>();
        for (Dwarf dwarf : dwarfs) {
            Integer currentCount = countOfDwarfsWithTheSameColor.get(dwarf.getColor());
            if (currentCount == null) {
                currentCount = 0;
            }
            countOfDwarfsWithTheSameColor.put(dwarf.getColor(), ++currentCount);
        }

        dwarfs.stream()
                .sorted((d1, d2) -> {
                    int result = Integer.compare(d2.getPhysics(), d1.getPhysics());
                    if (result == 0) {
                        int firstCountByTheSameColor = countOfDwarfsWithTheSameColor.get(d1.getColor());
                        int secondCountByTheSameColor = countOfDwarfsWithTheSameColor.get(d2.getColor());
                        result = Integer.compare(secondCountByTheSameColor, firstCountByTheSameColor);
                    }
                    return result;
                })
                .forEach(d -> System.out.printf("(%s) %s <-> %d%n", d.getColor(), d.getName(), d.getPhysics()));
        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }
}
