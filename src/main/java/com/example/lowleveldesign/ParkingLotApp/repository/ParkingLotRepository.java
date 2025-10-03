package com.example.lowleveldesign.ParkingLotApp.repository;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
