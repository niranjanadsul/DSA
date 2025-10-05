package com.example.lowleveldesign.ParkingLotApp.controller;

import com.example.lowleveldesign.ParkingLotApp.dto.*;
import com.example.lowleveldesign.ParkingLotApp.model.Payment;
import com.example.lowleveldesign.ParkingLotApp.model.Ticket;
import com.example.lowleveldesign.ParkingLotApp.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /**
     * Vehicle entry endpoint.
     * Example POST body:
     * { "licensePlate": "MH12AB1234", "vehicleType": "CAR" }
     */
    @PostMapping("/entry")
    public ResponseEntity<TicketResponse> entry(@RequestBody EntryRequest request) {
        // Build vehicle object (minimal fields needed for service)
        var vehicle = new com.example.lowleveldesign.ParkingLotApp.model.Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setVehicleType(request.getVehicleType());

        Ticket ticket = parkingService.handleVehicleEntry(vehicle);

        TicketResponse response = new TicketResponse(
                ticket.getId(),
                ticket.getSpot() != null ? ticket.getSpot().getId() : null,
                ticket.getVehicle() != null ? ticket.getVehicle().getLicensePlate() : null,
                ticket.getEntryTime(),
                ticket.getStatus()
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Vehicle exit endpoint.
     * Example POST body:
     * { "ticketId": 1 }
     */
    @PostMapping("/exit")
    public ResponseEntity<PaymentResponse> exit(@RequestBody ExitRequest request) {
        Payment payment = parkingService.handleVehicleExit(request.getTicketId());

        PaymentResponse response = new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getTimestamp(),
                payment.getStatus()
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Get ticket by id
     */
    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id) {
        var opt = parkingService.getTicketById(id);
        return opt.map(ticket -> ResponseEntity.ok(new TicketResponse(
                        ticket.getId(),
                        ticket.getSpot() != null ? ticket.getSpot().getId() : null,
                        ticket.getVehicle() != null ? ticket.getVehicle().getLicensePlate() : null,
                        ticket.getEntryTime(),
                        ticket.getStatus()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
