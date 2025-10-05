package com.example.lowleveldesign.ParkingLotApp.manager;

import com.example.lowleveldesign.ParkingLotApp.model.ParkingLot;

public class ParkingLotManager {

    private static ParkingLotManager instance;
    private final ParkingLot parkingLot;

    private ParkingLotManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public static synchronized ParkingLotManager getInstance(ParkingLot lot) {
        if (instance == null) {
            instance = new ParkingLotManager(lot);
        }
        return instance;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
