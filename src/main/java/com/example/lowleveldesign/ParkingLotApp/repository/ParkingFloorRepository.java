package com.example.lowleveldesign.ParkingLotApp.repository;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
}
