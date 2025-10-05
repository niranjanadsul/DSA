package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingLot;
import com.example.lowleveldesign.ParkingLotApp.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing Parking Lots.
 * Acts as an intermediary between the Controller and Repository.
 */
@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    // ✅ Constructor injection (preferred over @Autowired)
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    /**
     * Create or update a parking lot.
     */
    public ParkingLot saveParkingLot(ParkingLot lot) {
        return parkingLotRepository.save(lot);
    }

    /**
     * Get parking lot by ID.
     */
    public Optional<ParkingLot> getParkingLotById(Long id) {
        return parkingLotRepository.findById(id);
    }

    /**
     * Get list of all parking lots.
     */
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    /**
     * Delete a parking lot by ID.
     */
    public void deleteParkingLot(Long id) {
        parkingLotRepository.deleteById(id);
    }
}
