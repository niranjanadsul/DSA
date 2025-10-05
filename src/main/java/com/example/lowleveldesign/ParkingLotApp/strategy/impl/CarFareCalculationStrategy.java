package com.example.lowleveldesign.ParkingLotApp.strategy.impl;

import com.example.lowleveldesign.ParkingLotApp.model.Ticket;
import com.example.lowleveldesign.ParkingLotApp.strategy.FareCalculationStrategy;

import java.time.Duration;

public class CarFareCalculationStrategy implements FareCalculationStrategy {
    @Override
    public double calculateFare(Ticket ticket) {
        long hours = Math.max(1,
                Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours()
        );
        return 50 + 20 * hours;
    }
}
