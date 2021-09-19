package core;

import models.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleRepositoryImpl implements VehicleRepository {
    private final Map<String, Vehicle> vehiclesById;
    private final Map<String, Map<String, Vehicle>> vehiclesBySellers;
    private final Map<String, String> sellerByVehicleId;

    private final Map<String, TreeSet<Vehicle>> orderedVehiclesByBrand;
    private final Map<String, Map<String, Vehicle>> vehiclesByModel;
    private final Map<String, Map<String, Vehicle>> vehiclesByLocation;
    private final Map<String, Map<String, Vehicle>> vehiclesByColor;

    private final TreeMap<Double, TreeSet<Vehicle>> vehiclesByPrice;

    private final TreeMap<Integer, TreeMap<Double, TreeMap<String, Vehicle>>> ordered;

    private final Map<String, PriorityQueue<Vehicle>> vehiclesOrderedByPriceBySeller;

    public VehicleRepositoryImpl() {
        this.vehiclesById = new HashMap<>();
        this.vehiclesBySellers = new HashMap<>();
        this.sellerByVehicleId = new HashMap<>();

        this.orderedVehiclesByBrand = new HashMap<>();
        this.vehiclesByModel = new HashMap<>();
        this.vehiclesByLocation = new HashMap<>();
        this.vehiclesByColor = new HashMap<>();

        this.vehiclesByPrice = new TreeMap<>();

        this.ordered = new TreeMap<>((a, b) -> Integer.compare(b, a));

        this.vehiclesOrderedByPriceBySeller = new HashMap<>();
    }

    @Override
    public void addVehicleForSale(Vehicle vehicle, String sellerName) {
        if (vehicle != null && sellerName != null) {
            this.vehiclesById.put(vehicle.getId(), vehicle);
            this.vehiclesBySellers.computeIfAbsent(sellerName, k -> new LinkedHashMap<>()).put(vehicle.getId(), vehicle);
            this.sellerByVehicleId.put(vehicle.getId(), sellerName);
            this.ordered
                    .computeIfAbsent(vehicle.getHorsepower(), k -> new TreeMap<>())
                    .computeIfAbsent(vehicle.getPrice(), k -> new TreeMap<>())
                    .put(sellerName, vehicle);

            this.vehiclesOrderedByPriceBySeller.computeIfAbsent(sellerName, k -> new PriorityQueue<>(Comparator.comparing(Vehicle::getPrice))).offer(vehicle);

            this.addToIndices(vehicle);
        }
    }

    private void addToIndices(Vehicle vehicle) {
        this.orderedVehiclesByBrand.computeIfAbsent(vehicle.getBrand(), k -> new TreeSet<>(Comparator.comparing(Vehicle::getPrice).thenComparing(Vehicle::getId))).add(vehicle);
        this.vehiclesByModel.computeIfAbsent(vehicle.getModel(), k -> new HashMap<>()).put(vehicle.getId(), vehicle);
        this.vehiclesByLocation.computeIfAbsent(vehicle.getLocation(), k -> new HashMap<>()).put(vehicle.getId(), vehicle);
        this.vehiclesByColor.computeIfAbsent(vehicle.getColor(), k -> new HashMap<>()).put(vehicle.getId(), vehicle);
        this.vehiclesByPrice.computeIfAbsent(vehicle.getPrice(), k -> new TreeSet<>(Comparator.comparing(Vehicle::getHorsepower).reversed().thenComparing(Vehicle::getId))).add(vehicle);
    }

    @Override
    public void removeVehicle(String vehicleId) {
        Vehicle removedVehicle = this.vehiclesById.remove(vehicleId);

        if (removedVehicle == null) {
            throw new IllegalArgumentException();
        }

        this.removeFromIndices(removedVehicle);
    }

    private void removeFromIndices(Vehicle vehicle) {
        String id = vehicle.getId();
        String brand = vehicle.getBrand();
        String model = vehicle.getModel();
        String location = vehicle.getLocation();
        String color = vehicle.getColor();
        double price = vehicle.getPrice();

        String seller = this.sellerByVehicleId.remove(id);

        Map<String, Vehicle> sellerVehicles = this.vehiclesBySellers.get(seller);
        if (sellerVehicles.size() == 1) {
            this.vehiclesBySellers.remove(seller);
        } else {
            sellerVehicles.remove(id);
        }

        TreeSet<Vehicle> byBrand = this.orderedVehiclesByBrand.get(brand);
        if (byBrand.size() == 1) {
            this.orderedVehiclesByBrand.remove(brand);
        } else {
            byBrand.remove(vehicle);
        }

        Map<String, Vehicle> byModel = this.vehiclesByModel.get(model);
        if (byModel.size() == 1) {
            this.vehiclesByModel.remove(model);
        } else {
            byModel.remove(id);
        }

        Map<String, Vehicle> byLocation = this.vehiclesByLocation.get(location);
        if (byLocation.size() == 1) {
            this.vehiclesByLocation.remove(location);
        } else {
            byLocation.remove(id);
        }

        Map<String, Vehicle> byColor = this.vehiclesByColor.get(color);
        if (byColor.size() == 1) {
            this.vehiclesByColor.remove(color);
        } else {
            byColor.remove(id);
        }

        TreeSet<Vehicle> byPrice = this.vehiclesByPrice.get(price);
        if (byPrice.size() == 1) {
            this.vehiclesByPrice.remove(price);
        } else {
            byPrice.remove(vehicle);
        }

        // Level 1: By horsepower
        TreeMap<Double, TreeMap<String, Vehicle>> byHorsepower = this.ordered.get(vehicle.getHorsepower());
        if (byHorsepower.size() == 1) {
            this.ordered.remove(vehicle.getHorsepower());
        } else {
            // Level 2: By price
            TreeMap<String, Vehicle> thenByPrice = byHorsepower.get(price);
            if (thenByPrice.size() == 1) {
                byHorsepower.remove(price);
            } else {
                // Level 3: By seller name
                thenByPrice.remove(seller);
            }
        }

        PriorityQueue<Vehicle> vehicleQueue = this.vehiclesOrderedByPriceBySeller.get(seller);
        if (vehicleQueue.size() == 1) {
            this.vehiclesOrderedByPriceBySeller.remove(seller);
        } else {
            byBrand.remove(vehicle);
        }
    }

    @Override
    public int size() {
        return this.vehiclesById.size();
    }

    @Override
    public boolean contains(Vehicle vehicle) {
        if (vehicle != null) {
            return this.vehiclesById.containsKey(vehicle.getId());
        }

        return false;
    }

    @Override
    public Iterable<Vehicle> getVehicles(List<String> keywords) {
        if (keywords.isEmpty()) {
            return Collections.emptyList();
        }

        TreeSet<Vehicle> result = new TreeSet<>(Comparator.comparing(Vehicle::getIsVIP)
                .thenComparing(Vehicle::getPrice)
                .reversed().thenComparing(Vehicle::getId));

        for (String keyword : keywords) {
            TreeSet<Vehicle> brand = this.orderedVehiclesByBrand.get(keyword);

            if (brand != null) {
                result.addAll(brand);
            }

            Map<String, Vehicle> model = this.vehiclesByModel.get(keyword);

            if (model != null) {
                result.addAll(model.values());
            }

            Map<String, Vehicle> location = this.vehiclesByLocation.get(keyword);

            if (location != null) {
                result.addAll(location.values());
            }

            Map<String, Vehicle> color = this.vehiclesByColor.get(keyword);

            if (color != null) {
                result.addAll(color.values());
            }
        }

        return result;
    }

    @Override
    public Iterable<Vehicle> getVehiclesBySeller(String sellerName) {
        Map<String, Vehicle> vehicles = this.vehiclesBySellers.get(sellerName);

        if (vehicles == null) {
            throw new IllegalArgumentException();
        }

        return vehicles.values();
    }

    @Override
    public Iterable<Vehicle> getVehiclesInPriceRange(double lowerBound, double upperBound) {
        NavigableMap<Double, TreeSet<Vehicle>> vehiclesInRange = this.vehiclesByPrice.subMap(lowerBound, true, upperBound, true);

        if (vehiclesInRange == null) {
            return Collections.emptyList();
        }

        return vehiclesInRange
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Vehicle>> getAllVehiclesGroupedByBrand() {
        if (this.vehiclesById.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Map<String, List<Vehicle>> result = new HashMap<>();
        for (Map.Entry<String, TreeSet<Vehicle>> entry : this.orderedVehiclesByBrand.entrySet()) {
            result.computeIfAbsent(entry.getKey(), k -> new LinkedList<>()).addAll(entry.getValue());
        }

        return result;
    }

    @Override
    public Iterable<Vehicle> getAllVehiclesOrderedByHorsepowerDescendingThenByPriceThenBySellerName() {
        return this.ordered
                .values()
                .stream()
                .flatMap(l -> l
                        .values()
                        .stream()
                        .flatMap(m -> m
                                .values()
                                .stream()))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle buyCheapestFromSeller(String sellerName) {
        PriorityQueue<Vehicle> vehicles = this.vehiclesOrderedByPriceBySeller.get(sellerName);
        if (vehicles != null) {
            if (!vehicles.isEmpty()) {
                return vehicles.poll();
            }
        }

        throw new IllegalArgumentException();
    }
}