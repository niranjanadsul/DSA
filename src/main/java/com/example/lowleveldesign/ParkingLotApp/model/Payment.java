package com.example.lowleveldesign.ParkingLotApp.model;

import com.example.lowleveldesign.ParkingLotApp.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;
}
