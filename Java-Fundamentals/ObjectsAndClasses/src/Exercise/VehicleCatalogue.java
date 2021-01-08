package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleCatalogue {

    static class Car {
        String typeOfVehicle;
        String model;
        String color;
        int horsepower;

        Car(String model, String color, int horsepower) {
            this.typeOfVehicle = "Car";
            this.model = model;
            this.color = color;
            this.horsepower = horsepower;
        }

        @Override
        public String toString() {
            return String.format("Type: %s%n" +
                            "Model: %s%n" +
                            "Color: %s%n" +
                            "Horsepower: %d",
                    this.typeOfVehicle, this.model, this.color, this.horsepower);
        }

        String getMode() {
            return this.model;
        }
    }

    static class Truck {
        String typeOfVehicle;
        String model;
        String color;
        int horsepower;

        Truck(String model, String color, int horsepower) {
            this.typeOfVehicle = "Truck";
            this.model = model;
            this.color = color;
            this.horsepower = horsepower;
        }

        @Override
        public String toString() {
            return String.format("Type: %s%n" +
                            "Model: %s%n" +
                            "Color: %s%n" +
                            "Horsepower: %d",
                    this.typeOfVehicle, this.model, this.color, this.horsepower);
        }

        String getMode() {
            return this.model;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        List<Truck> trucks = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        double trucksHorsepower = 0.00;
        double carsHorsepower = 0.00;

        int carsCounter = 0;
        int trucksCounter = 0;

        while (!line.equals("End")) {

            String[] data = line.split(" ");
            int horsepower = Integer.parseInt(data[3]);
            if (data[0].equalsIgnoreCase("car")) {
                Car car = new Car(data[1], data[2], horsepower);
                cars.add(car);
                carsHorsepower += horsepower;
                carsCounter++;

            } else {
                Truck truck = new Truck(data[1], data[2], horsepower);
                trucks.add(truck);
                trucksHorsepower += horsepower;
                trucksCounter++;
            }

            line = scanner.nextLine();
        }

        String searchingModel = scanner.nextLine();

        while (!searchingModel.equals("Close the Catalogue")) {
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getMode().equals(searchingModel)) {
                    System.out.println(cars.get(i));
                    break;
                }
            }

            for (int i = 0; i < trucks.size(); i++) {
                if (trucks.get(i).getMode().equals(searchingModel)) {
                    System.out.println(trucks.get(i));
                    break;
                }
            }

            searchingModel = scanner.nextLine();
        }
        if (carsCounter == 0) {
            System.out.printf("Cars have average horsepower of: %.2f.%n", carsHorsepower);
        } else {
            System.out.printf("Cars have average horsepower of: %.2f.%n", carsHorsepower / carsCounter);
        }


        if (trucksCounter == 0) {
            System.out.printf("Trucks have average horsepower of: %.2f.%n", trucksHorsepower);
        } else {
            System.out.printf("Trucks have average horsepower of: %.2f.", trucksHorsepower / trucksCounter);
        }
    }
}
