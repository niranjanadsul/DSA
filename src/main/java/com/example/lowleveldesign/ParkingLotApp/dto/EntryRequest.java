package com.example.lowleveldesign.ParkingLotApp.dto;

import com.example.lowleveldesign.ParkingLotApp.model.enums.VehicleType;

public class EntryRequest {
    private String licensePlate;
    private VehicleType vehicleType;

    public EntryRequest() {}

    public EntryRequest(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
}
