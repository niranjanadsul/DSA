package com.example.lowleveldesign.ParkingLotApp.factory;

import com.example.lowleveldesign.ParkingLotApp.model.enums.VehicleType;
import com.example.lowleveldesign.ParkingLotApp.strategy.FareCalculationStrategy;
import com.example.lowleveldesign.ParkingLotApp.strategy.impl.BikeFareCalculationStrategy;
import com.example.lowleveldesign.ParkingLotApp.strategy.impl.CarFareCalculationStrategy;

public class FareCalculationFactory {

    public static FareCalculationStrategy getStrategy(VehicleType type) {
        return switch(type) {
            case CAR -> new CarFareCalculationStrategy();
            case BIKE -> new BikeFareCalculationStrategy();
            case TRUCK -> throw new IllegalArgumentException("Truck fare not implemented yet");
        };
    }
}
