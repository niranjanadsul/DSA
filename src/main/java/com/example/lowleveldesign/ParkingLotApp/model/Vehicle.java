package com.example.lowleveldesign.ParkingLotApp.model;

import com.example.lowleveldesign.ParkingLotApp.model.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
