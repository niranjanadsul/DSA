package com.example.lowleveldesign.ParkingLotApp.model;

import com.example.lowleveldesign.ParkingLotApp.model.enums.SpotStatus;
import com.example.lowleveldesign.ParkingLotApp.model.enums.SpotType;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotStatus status = SpotStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private ParkingFloor floor;

    @OneToOne(mappedBy = "spot")
    private Ticket currentTicket;
}
