package core;

import models.Doodle;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DoodleSearchImpl implements DoodleSearch {
    //            Id    Doodle
    private Map<String, Doodle> doodlesById;
    //          Title   Doodle
    private Map<String, Doodle> doodlesByTitle;

    private double totalRevenueFromAds;

    public DoodleSearchImpl() {
        this.doodlesById = new LinkedHashMap<>();
        this.doodlesByTitle = new LinkedHashMap<>();
    }

    @Override
    public void addDoodle(Doodle doodle) {
        this.doodlesById.put(doodle.getId(), doodle);
        this.doodlesByTitle.put(doodle.getTitle(), doodle);

        if (doodle.getIsAd()) {
            this.totalRevenueFromAds += doodle.getVisits() * doodle.getRevenue();
        }
    }

    @Override
    public void removeDoodle(String doodleId) {
        Doodle removedDoodle = this.doodlesById.remove(doodleId);

        if (removedDoodle == null) {
            throw new IllegalArgumentException();
        }

        this.doodlesByTitle.remove(removedDoodle.getTitle());

        if (removedDoodle.getIsAd()) {
            this.totalRevenueFromAds -= removedDoodle.getVisits() * removedDoodle.getRevenue();
        }
    }

    @Override
    public int size() {
        return this.doodlesById.size();
    }

    @Override
    public boolean contains(Doodle doodle) {
        return this.doodlesById.containsKey(doodle.getId());
    }

    @Override
    public Doodle getDoodle(String id) {
        Doodle doodle = this.doodlesById.get(id);

        if (doodle == null) {
            throw new IllegalArgumentException();
        }

        return doodle;
    }

    @Override
    public double getTotalRevenueFromDoodleAds() {
        return this.totalRevenueFromAds;
    }

    @Override
    public void visitDoodle(String title) {
        Doodle searchedDoodle = this.doodlesByTitle.get(title);

        if (searchedDoodle == null) {
            throw new IllegalArgumentException();
        }

        searchedDoodle.setVisits(searchedDoodle.getVisits() + 1);
    }

    @Override
    public Iterable<Doodle> searchDoodles(String searchQuery) {
        return this.doodlesByTitle
                .entrySet()
                .stream()
                .filter(e -> e.getKey().contains(searchQuery))
                .map(Map.Entry::getValue)
                .sorted((d1, d2) -> {
                    int result = Boolean.compare(d2.getIsAd(), d1.getIsAd());

                    if (result == 0) {
                        result = d1.getTitle().indexOf(searchQuery) - d2.getTitle().indexOf(searchQuery);
                    }

                    if (result == 0) {
                        result = d2.getVisits() - d1.getVisits();
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Doodle> getDoodleAds() {
        return this.doodlesById
                .values()
                .stream()
                .filter(Doodle::getIsAd)
                .sorted(Comparator.comparingDouble(Doodle::getRevenue)
                        .thenComparingInt(Doodle::getVisits).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Doodle> getTop3DoodlesByRevenueThenByVisits() {
        return this.doodlesById
                .values()
                .stream()
                .sorted(Comparator.comparingInt(Doodle::getVisits).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}