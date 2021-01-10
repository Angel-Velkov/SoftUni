package Exercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {
    private static class Demon {
        String name;
        int health;
        double damage;

        Demon(String name, int health, double damage) {
            this.name = name;
            this.health = health;
            this.damage = damage;
        }

        public String getName() {
            return this.name;
        }

        public int getHealth() {
            return this.health;
        }

        public double getDamage() {
            return this.damage;
        }
    }

    public static void main(String[] args) {

        Pattern demonHealthPattern = Pattern.compile("[^0-9+\\-*/.]");
        Pattern demonDamagePattern = Pattern.compile("(?<sign>[+-]?)(?<number>\\d+(\\.\\d+)?)");

        String[] input = new Scanner(System.in).nextLine().split(",\\s*");

        List<Demon> demons = new ArrayList<>(input.length);

        for (int i = 0; i < input.length; i++) {

            String currentName = input[i].trim();

            int health = calculateDemonsHealth(demonHealthPattern, currentName);
            double damage = calculateDemonsDamage(demonDamagePattern, currentName);

            Demon demon = new Demon(currentName, health, damage);
            demons.add(demon);
        }

        demons.sort(Comparator.comparing(Demon::getName));

        for (Demon d : demons) {
            System.out.printf("%s - %d health, %.2f damage%n"
                    , d.getName(), d.getHealth(), d.getDamage());
        }
    }

    private static int calculateDemonsHealth(Pattern p, String name) {
        int health = 0;
        Matcher matcher = p.matcher(name);
        while (matcher.find()) {
            health += (matcher.group().charAt(0));
        }
        return health;
    }

    private static double calculateDemonsDamage(Pattern p, String name) {
        double damage = 0;
        Matcher matcher = p.matcher(name);

        while (matcher.find()) {
            String sign = matcher.group("sign");
            double number = Double.parseDouble(matcher.group("number"));

            if (sign.equals("-")) {
                damage -= number;
            } else {
                damage += number;
            }
        }

        int multiplier = name.replaceAll("[^*]", "").length();
        int divisor = name.replaceAll("[^/]", "").length();

        for (int i = 0; i < Math.abs(multiplier - divisor); i++) {
            if (multiplier > divisor) {
                damage *= 2;
            } else {
                damage /= 2;
            }
        }
        return damage;
    }
}
