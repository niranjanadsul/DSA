package com.example.lowleveldesign.ParkingLotApp.strategy;

import com.example.lowleveldesign.ParkingLotApp.model.Ticket;

public interface FareCalculationStrategy {
    double calculateFare(Ticket ticket);
}
