package com.example.xmlcardealer.service;

import com.example.xmlcardealer.model.dto.CarSeedDto;
import com.example.xmlcardealer.model.entity.Car;

import java.util.Collection;

public interface CarService {

    void seedCars(Collection<CarSeedDto> cars);

    Car getRandomCar();
}
