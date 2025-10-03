package com.example.lowleveldesign.ParkingLotApp.repository;

import com.example.lowleveldesign.ParkingLotApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
