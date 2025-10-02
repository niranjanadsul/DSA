package com.example.lowleveldesign.ParkingLotApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private ParkingFloor floor;
}

enum SpotType {
    COMPACT, LARGE, HANDICAPPED
}

