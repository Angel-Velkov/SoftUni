package MoreExercise;

import java.util.*;

public class DragonArmy {
    private static class Dragon {
        String name;
        int health;
        int damage;
        int armor;

        Dragon(String name, String damage, String health, String armor) {
            this.name = name;
            this.damage = parseDamage(damage);
            this.health = parseHealth(health);
            this.armor = parseArmor(armor);
        }

        public void setStats(String damage, String health, String armor) {
            this.damage = parseDamage(damage);
            this.health = parseHealth(health);
            this.armor = parseArmor(armor);
        }

        private int parseDamage(String damage) {
            if (damage.equals("null")) {
                return 45;
            }
            return Integer.parseInt(damage);

        }

        private int parseHealth(String health) {
            if (health.equals("null")) {
                return 250;
            }
            return Integer.parseInt(health);
        }

        private int parseArmor(String armor) {
            if (armor.equals("null")) {
                return 10;
            }
            return Integer.parseInt(armor);

        }

        public int getDamage() {
            return damage;
        }

        public int getHealth() {
            return health;
        }

        public int getArmor() {
            return armor;
        }
    }

    public static void main(String[] args) {
        Map<String, Map<String, Dragon>> dragons = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] dragonData = scanner.nextLine().split("\\s+");
            String type = dragonData[0];
            String name = dragonData[1];
            String damage = dragonData[2];
            String health = dragonData[3];
            String armor = dragonData[4];

            if (checkingIfTheDragonAlreadyExist(dragons, type, name)) {
                dragons.get(type).get(name).setStats(damage, health, armor);
            } else {
                Dragon d = new Dragon(name, damage, health, armor);
                if (!dragons.containsKey(type)) {
                    Map<String, Dragon> dragStats = new HashMap<>();
                    dragStats.put(name, d);
                    dragons.put(type, dragStats);
                } else {
                    dragons.get(type).put(name, d);
                }
            }
        }
        for (Map.Entry<String, Map<String, Dragon>> dragonsFromSameType : dragons.entrySet()) {
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", dragonsFromSameType.getKey(),
                    dragonsFromSameType.getValue().values().stream().mapToInt(Dragon::getDamage).average().orElse(0),
                    dragonsFromSameType.getValue().values().stream().mapToInt(Dragon::getHealth).average().orElse(0),
                    dragonsFromSameType.getValue().values().stream().mapToInt(Dragon::getArmor).average().orElse(0));
            dragonsFromSameType
                    .getValue()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(d -> System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n"
                            , d.getKey(), d.getValue().getDamage(), d.getValue().getHealth(), d.getValue().getArmor()));
        }
    }

    private static boolean checkingIfTheDragonAlreadyExist(Map<String, Map<String, Dragon>> dragons, String type, String name) {
        for (Map.Entry<String, Map<String, Dragon>> dragon : dragons.entrySet()) {
            if (dragon.getKey().equals(type)) {
                if (dragon.getValue().containsKey(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
