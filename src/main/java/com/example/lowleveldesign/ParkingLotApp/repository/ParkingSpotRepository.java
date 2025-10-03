package com.example.lowleveldesign.ParkingLotApp.repository;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
}
