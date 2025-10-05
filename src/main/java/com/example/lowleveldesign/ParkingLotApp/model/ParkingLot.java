package com.example.lowleveldesign.ParkingLotApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingFloor> floors;
}
