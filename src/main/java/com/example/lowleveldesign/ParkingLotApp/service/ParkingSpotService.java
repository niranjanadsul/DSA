package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingSpot;
import com.example.lowleveldesign.ParkingLotApp.repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing individual Parking Spots.
 */
@Service
public class ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingSpot saveSpot(ParkingSpot spot) {
        return parkingSpotRepository.save(spot);
    }

    public Optional<ParkingSpot> getSpotById(Long id) {
        return parkingSpotRepository.findById(id);
    }

    public List<ParkingSpot> getAllSpots() {
        return parkingSpotRepository.findAll();
    }

    public void deleteSpot(Long id) {
        parkingSpotRepository.deleteById(id);
    }
}
