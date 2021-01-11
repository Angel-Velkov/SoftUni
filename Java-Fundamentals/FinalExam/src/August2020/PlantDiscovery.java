package August2020;

import java.util.*;

public class PlantDiscovery {
    public static class Plant {
        String name;

        int rarity;
        List<Double> ratings;

        Plant(String name, int rarity) {
            this.name = name;
            this.rarity = rarity;
            ratings = new ArrayList<>();
        }

        public String getName() {
            return this.name;
        }

        public int getRarity() {
            return rarity;
        }

        public void setRarity(int rarity) {
            this.rarity = rarity;
        }

        public void addRating(double rating) {
            ratings.add(rating);
        }

        public void resetRating() {
            this.ratings.clear();
        }

        public double getAverageRating() {
            if (this.ratings.isEmpty()) {
                return 0;
            }
            double averageRating = 0;
            for (Double rating : this.ratings) {
                averageRating += rating;
            }
            averageRating = averageRating / this.ratings.size();

            return averageRating;
        }
    }

    public static void main(String[] args) {
        Map<String, Plant> plantByName = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("<->");
            Plant p = new Plant(input[0], Integer.parseInt(input[1]));
            plantByName.put(p.getName(), p);
        }

        String line = scanner.nextLine();
        while (!line.equals("Exhibition")) {
            String[] commandsAndParameters = line.split(": | - ");
            String command = commandsAndParameters[0];
            String plant = commandsAndParameters[1];

            if (plantByName.get(plant) == null) {
                System.out.println("error");
            } else {
                switch (command) {
                    case "Rate":
                        double rating = Double.parseDouble(commandsAndParameters[2]);
                        plantByName.get(plant).addRating(rating);
                        break;
                    case "Update":
                        int newRarity = Integer.parseInt(commandsAndParameters[2]);
                        plantByName.get(plant).setRarity(newRarity);
                        break;
                    case "Reset":
                        plantByName.get(plant).resetRating();
                        break;
                    default:
                        System.out.println("error");
                }
            }

            line = scanner.nextLine();
        }
        System.out.println("Plants for the exhibition:");
        plantByName
                .values()
                .stream()
                .sorted((a, b) -> {
                    if (a.getRarity() != b.getRarity()) {
                        return Integer.compare(b.getRarity(), a.getRarity());
                    } else {
                        return Double.compare(b.getAverageRating(), a.getAverageRating());
                    }
                })
                .map(p -> String.format("- %s; Rarity: %d; Rating: %.2f", p.getName(), p.getRarity(), p.getAverageRating()))
                .forEach(System.out::println);
    }
}
