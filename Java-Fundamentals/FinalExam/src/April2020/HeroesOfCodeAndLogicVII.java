package April2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HeroesOfCodeAndLogicVII {
    public static class Hero {
        String name;
        int HP;
        int MP;

        Hero(String name, int HP, int MP) {
            this.name = name;
            this.HP = HP;
            this.MP = MP;
        }

        public String getName() {
            return this.name;
        }

        public int getMP() {
            return this.MP;
        }

        public int getHP() {
            return this.HP;
        }

        public void setMP(int MP) {
            this.MP = MP;
        }

        public void setHP(int HP) {
            this.HP = HP;
        }
    }


    public static void main(String[] args) {
        Map<String, Hero> heroesByName = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] heroParameters = scanner.nextLine().split("\\s+");
            String name = heroParameters[0];
            int HP = Math.min(Integer.parseInt(heroParameters[1]), 100);
            int MP = Math.min(Integer.parseInt(heroParameters[2]), 200);

            Hero h = new Hero(name, HP, MP);
            heroesByName.put(h.getName(), h);
        }

        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String[] data = command.split(" - ");
            String action = data[0];
            String heroName = data[1];
            switch (action) {
                case "CastSpell":
                    int neededMP = Integer.parseInt(data[2]);
                    String spellName = data[3];

                    castingSpell(heroesByName, heroName, neededMP, spellName);
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(data[2]);
                    String attackerName = data[3];

                    takingDamage(heroesByName, heroName, damage, attackerName);
                    break;
                case "Recharge":
                    int addsMP = Integer.parseInt(data[2]);

                    increasingHeroesMP(heroesByName, heroName, addsMP);
                    break;
                case "Heal":
                    int addsHP = Integer.parseInt(data[2]);

                    increasingHeroesHP(heroesByName, heroName, addsHP);
                    break;
                default:
                    System.out.println("Invalid command!!!");
            }

            command = scanner.nextLine();
        }

        heroesByName
                .values()
                .stream()
                .sorted((h1, h2) -> {
                    int result = h2.getHP() - h1.getHP();
                    if (result == 0) {
                        result = h1.getName().compareTo(h2.getName());
                    }
                    return result;
                })
                .forEach(h -> {
                    System.out.println(h.getName());
                    System.out.println("  HP: " + h.getHP());
                    System.out.println("  MP: " + h.getMP());
                });
    }

    private static void increasingHeroesHP(Map<String, Hero> heroesByName, String heroName, int addsHP) {
        Hero hero = heroesByName.get(heroName);
        int currentHP = hero.getHP();
        if (currentHP + addsHP > 100) {
            addsHP = 100 - currentHP;
        }
        hero.setHP(currentHP + addsHP);

        System.out.printf("%s healed for %d HP!%n", heroName, addsHP);
    }

    private static void increasingHeroesMP(Map<String, Hero> heroesByName, String heroName, int addsMP) {
        Hero hero = heroesByName.get(heroName);
        int currentMP = hero.getMP();
        if (currentMP + addsMP > 200) {
            addsMP = 200 - currentMP;
        }
        hero.setMP(currentMP + addsMP);

        System.out.printf("%s recharged for %d MP!%n", heroName, addsMP);
    }

    private static void takingDamage(Map<String, Hero> heroesByName, String heroName, int damage, String attackerName) {
        Hero hero = heroesByName.get(heroName);
        int currentHP = hero.getHP() - damage;

        if (currentHP <= 0) {
            heroesByName.remove(heroName);
            System.out.printf("%s has been killed by %s!%n", heroName, attackerName);
        } else {
            hero.setHP(currentHP);
            System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", heroName, damage, attackerName, currentHP);
        }
    }

    private static void castingSpell(Map<String, Hero> heroesByName, String heroName, int neededMP, String spellName) {
        Hero hero = heroesByName.get(heroName);
        int currentMP = hero.getMP() - neededMP;

        if (currentMP < 0) {
            System.out.printf("%s does not have enough MP to cast %s!%n", heroName, spellName);
        } else {
            hero.setMP(currentMP);
            System.out.printf("%s has successfully cast %s and now has %d MP!%n", heroName, spellName, currentMP);
        }
    }
}
