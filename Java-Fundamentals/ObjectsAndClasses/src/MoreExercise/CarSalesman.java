package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarSalesman {
    private static class Car {
        String model;
        Engine engine;
        String weight = "n/a";
        String color = "n/a";

        Car(String model, Engine engine) {
            this.model = model;
            this.engine = engine;
        }

        private String getModel() {
            return this.model;
        }

        private Engine getEngine() {
            return this.engine;
        }

        private String getWeight() {
            return this.weight;
        }

        private String getColor() {
            return this.color;
        }

        private void setWeight(String weight) {
            this.weight = weight;
        }

        private void setColor(String color) {
            this.color = color;
        }
    }

    private static class Engine {
        String model;
        String power;
        String displacement = "n/a";
        String efficiency = "n/a";

        Engine(String model, String power) {
            this.model = model;
            this.power = power;
        }

        private String getModel() {
            return this.model;
        }

        public String getPower() {
            return power;
        }

        public String getDisplacement() {
            return displacement;
        }

        public String getEfficiency() {
            return efficiency;
        }

        private void setDisplacement(String displacement) {
            this.displacement = displacement;
        }

        private void setEfficiency(String efficiency) {
            this.efficiency = efficiency;
        }

    }

    public static void main(String[] args) {
        List<Engine> engineList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] engineData = scanner.nextLine().split("\\s+");
            String model = engineData[0];
            String power = engineData[1];

            Engine engine = new Engine(model, power);

            switch (engineData.length) {
                case 3: {
                    if (checksIfItIsANumberOrString(engineData[2])) {
                        String displacement = engineData[2];
                        engine.setDisplacement(displacement);
                    } else {
                        String efficiency = engineData[2];
                        engine.setEfficiency(efficiency);
                    }
                }
                break;
                case 4: {
                    String displacement;
                    String efficiency;
                    if (checksIfItIsANumberOrString(engineData[2])) {
                        displacement = engineData[2];
                        efficiency = engineData[3];
                    } else {
                        efficiency = engineData[2];
                        displacement = engineData[3];
                    }
                    engine.setDisplacement(displacement);
                    engine.setEfficiency(efficiency);
                }
            }
            engineList.add(engine);
        }

        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            String[] carData = scanner.nextLine().split("\\s+");
            String model = carData[0];
            Engine engine = returnsEngineFromTheModel(engineList, carData[1]);

            Car car = new Car(model, engine);
            switch (carData.length) {
                case 3: {
                    if (checksIfItIsANumberOrString(carData[2])) {
                        String weight = carData[2];
                        car.setWeight(weight);
                    } else {
                        String color = carData[2];
                        car.setColor(color);
                    }
                }
                break;
                case 4: {
                    String weight;
                    String color;
                    if (checksIfItIsANumberOrString(carData[2])) {
                        weight = carData[2];
                        color = carData[3];
                    } else {
                        color = carData[2];
                        weight = carData[3];
                    }
                    car.setWeight(weight);
                    car.setColor(color);
                }
                break;
            }
            carList.add(car);
        }

        for (Car car : carList) {
            System.out.println(car.getModel() + ":");
            System.out.println(car.getEngine().getModel() + ":");
            System.out.println("Power: " + car.getEngine().getPower());
            System.out.println("Displacement: " + car.getEngine().getDisplacement());
            System.out.println("Efficiency: " + car.getEngine().getEfficiency());
            System.out.println("Weight: " + car.getWeight());
            System.out.println("Color: " + car.getColor());
        }
    }

    private static Engine returnsEngineFromTheModel(List<Engine> engineList, String model) {
        for (Engine engine : engineList) {
            if (engine.getModel().equals(model)) {
                return engine;
            }
        }
        return null;
    }

    static boolean checksIfItIsANumberOrString(String line) {
        for (int j = 0; j < line.length(); j++) {
            char currentChar = line.charAt(j);
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }
        return true;
    }
}
