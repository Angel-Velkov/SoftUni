package com.example.cardealer.service;

import com.example.cardealer.model.dto.CarDto;
import com.example.cardealer.model.entity.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {

    void seedCars() throws IOException;

    Car getRandomCar();

    List<CarDto> findAllByMake(String make);
}
