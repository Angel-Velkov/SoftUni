package com.example.aop.sla;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    @TrackLatency(latency = "local_operation")
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(List.of(
                new Driver("Pesho", "C"),
                new Driver("Mitko", "D")
        ));
    }

    @TrackLatency(latency = "remote_operation")
    @GetMapping("/sync-drivers")
    public ResponseEntity<List<Driver>> loadAllDrivers() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        return ResponseEntity.ok(List.of(
                new Driver("Pesho", "C"),
                new Driver("Mitko", "D")
        ));
    }
}
