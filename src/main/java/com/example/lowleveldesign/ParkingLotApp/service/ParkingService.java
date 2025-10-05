package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.factory.FareCalculationFactory;
import com.example.lowleveldesign.ParkingLotApp.model.*;
import com.example.lowleveldesign.ParkingLotApp.model.enums.SpotStatus;
import com.example.lowleveldesign.ParkingLotApp.model.enums.SpotType;
import com.example.lowleveldesign.ParkingLotApp.model.enums.VehicleType;
import com.example.lowleveldesign.ParkingLotApp.observer.NotificationObserver;
import com.example.lowleveldesign.ParkingLotApp.repository.ParkingSpotRepository;
import com.example.lowleveldesign.ParkingLotApp.repository.PaymentRepository;
import com.example.lowleveldesign.ParkingLotApp.repository.TicketRepository;
import com.example.lowleveldesign.ParkingLotApp.repository.VehicleRepository;
import com.example.lowleveldesign.ParkingLotApp.strategy.FareCalculationStrategy;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final VehicleRepository vehicleRepository;
    private final TicketRepository ticketRepository;
    private final ParkingSpotRepository spotRepository;
    private final PaymentRepository paymentRepository;
    private final List<NotificationObserver> observers;

    public ParkingService(VehicleRepository vehicleRepository,
                          TicketRepository ticketRepository,
                          ParkingSpotRepository spotRepository,
                          PaymentRepository paymentRepository,
                          List<NotificationObserver> observers) {
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.spotRepository = spotRepository;
        this.paymentRepository = paymentRepository;
        this.observers = observers;
    }

    // Vehicle Entry
    public Ticket handleVehicleEntry(Vehicle vehicle) {

        vehicleRepository.save(vehicle);

        SpotType requiredType = switch(vehicle.getVehicleType()) {
            case BIKE -> SpotType.COMPACT;
            case CAR -> SpotType.LARGE;
            case TRUCK -> SpotType.HANDICAPPED;
        };

        ParkingSpot freeSpot = spotRepository.findAll().stream()
                .filter(s -> s.getSpotType() == requiredType && s.getStatus() == SpotStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No free spot available"));

        freeSpot.setStatus(SpotStatus.OCCUPIED);
        spotRepository.save(freeSpot);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setSpot(freeSpot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(com.example.lowleveldesign.ParkingLotApp.model.enums.TicketStatus.ACTIVE);
        ticketRepository.save(ticket);

        notifyAllObservers("Vehicle " + vehicle.getLicensePlate() + " entered. Ticket ID: " + ticket.getId());

        return ticket;
    }

    // Vehicle Exit & Payment
    public Payment handleVehicleExit(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setExitTime(LocalDateTime.now());
        ticket.setStatus(com.example.lowleveldesign.ParkingLotApp.model.enums.TicketStatus.COMPLETED);
        ticketRepository.save(ticket);

        FareCalculationStrategy strategy = FareCalculationFactory.getStrategy(ticket.getVehicle().getVehicleType());
        double fare = strategy.calculateFare(ticket);

        ParkingSpot spot = ticket.getSpot();
        spot.setStatus(SpotStatus.AVAILABLE);
        spotRepository.save(spot);

        Payment payment = new Payment();
        payment.setAmount(fare);
        payment.setTimestamp(LocalDateTime.now());
        payment.setStatus(com.example.lowleveldesign.ParkingLotApp.model.enums.PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        notifyAllObservers("Vehicle " + ticket.getVehicle().getLicensePlate() + " exited. Fare: " + fare);

        return payment;
    }

    private void notifyAllObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.notify(message);
        }
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
