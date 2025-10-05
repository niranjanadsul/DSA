package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingFloor;
import com.example.lowleveldesign.ParkingLotApp.repository.ParkingFloorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing Parking Floors within a Parking Lot.
 */
@Service
public class ParkingFloorService {

    private final ParkingFloorRepository parkingFloorRepository;

    public ParkingFloorService(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    public ParkingFloor saveFloor(ParkingFloor floor) {
        return parkingFloorRepository.save(floor);
    }

    public Optional<ParkingFloor> getFloorById(Long id) {
        return parkingFloorRepository.findById(id);
    }

    public List<ParkingFloor> getAllFloors() {
        return parkingFloorRepository.findAll();
    }

    public void deleteFloor(Long id) {
        parkingFloorRepository.deleteById(id);
    }
}
