package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RawData {
    public static class Car {
        String model;
        Engine engine;
        Cargo cargo;
        Tire tires;

        Car(String model, Engine engine, Cargo cargo, Tire tires) {
            this.model = model;
            this.engine = engine;
            this.cargo = cargo;
            this.tires = tires;
        }

        public String getModel() {
            return model;
        }

        public Engine getEngine() {
            return engine;
        }

        public Cargo getCargo() {
            return cargo;
        }

        public Tire getTires() {
            return tires;
        }

    }

    public static class Tire {

        double[] tiresPressure = new double[4];

        int[] tiresAge = new int[4];

        public Tire(double tire1Pressure, double tire2Pressure, double tire3Pressure, double tire4Pressure
                , int tire1Age, int tire2Age, int tire3Age, int tire4Age) {
            this.tiresPressure[0] = tire1Pressure;
            this.tiresPressure[1] = tire2Pressure;
            this.tiresPressure[2] = tire3Pressure;
            this.tiresPressure[3] = tire4Pressure;
            this.tiresAge[0] = tire1Age;
            this.tiresAge[1] = tire2Age;
            this.tiresAge[2] = tire3Age;
            this.tiresAge[3] = tire4Age;

        }

        public double[] getTiresPressure() {
            return tiresPressure;
        }
    }

    private static class Engine {
        int engineSpeed;
        int enginePower;

        Engine(int engineSpeed, int enginePower) {
            this.engineSpeed = engineSpeed;
            this.enginePower = enginePower;
        }

        public int getEnginePower() {
            return this.enginePower;
        }
    }

    public static class Cargo {
        String cargoType;
        int cargoWeight;

        Cargo(String cargoType, int cargoWeight) {
            this.cargoType = cargoType;
            this.cargoWeight = cargoWeight;
        }

        public String getCargoType() {
            return cargoType;
        }
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] carData = scanner.nextLine().split("\\s+");
            String model = carData[0];
            int engineSpeed = Integer.parseInt(carData[1]);
            int enginePower = Integer.parseInt(carData[2]);
            int cargoWeight = Integer.parseInt(carData[3]);
            String cargoType = carData[4];
            double tire1Pressure = Double.parseDouble(carData[5]);
            int tire1Age = Integer.parseInt(carData[6]);
            double tire2Pressure = Double.parseDouble(carData[7]);
            int tire2Age = Integer.parseInt(carData[8]);
            double tire3Pressure = Double.parseDouble(carData[9]);
            int tire3Age = Integer.parseInt(carData[10]);
            double tire4Pressure = Double.parseDouble(carData[11]);
            int tire4Age = Integer.parseInt(carData[12]);

            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoType, cargoWeight);
            Tire tires = new Tire(tire1Pressure, tire2Pressure, tire3Pressure, tire4Pressure
                    , tire1Age, tire2Age, tire3Age, tire4Age);
            Car car = new Car(model, engine, cargo, tires);
            cars.add(car);
        }

        String command = scanner.nextLine();
        switch (command) {
            case "fragile":
                for (Car car : cars) {
                    if (car.getCargo().getCargoType().equals("fragile")) {
                        for (double t : car.getTires().getTiresPressure()) {
                            if (t < 1) {
                                System.out.println(car.getModel());
                                break;
                            }
                        }

                    }
                }
                break;
            case "flamable":
                for (Car car : cars) {
                    if (car.getCargo().getCargoType().equals("flamable")) {
                        if (car.getEngine().getEnginePower() > 250) {
                            System.out.println(car.getModel());
                        }
                    }
                }
                break;
        }
    }
}
