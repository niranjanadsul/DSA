package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.Vehicle;
import com.example.lowleveldesign.ParkingLotApp.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Handles registration, lookup, and management of vehicles.
 */
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
