package com.example.lowleveldesign.ParkingLotApp.controller;

import com.example.lowleveldesign.ParkingLotApp.dto.*;
import com.example.lowleveldesign.ParkingLotApp.model.*;
import com.example.lowleveldesign.ParkingLotApp.model.enums.*;
import com.example.lowleveldesign.ParkingLotApp.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller layer test for ParkingController.
 * Uses MockMvc to simulate HTTP requests.
 */
@WebMvcTest(ParkingController.class)
class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test /api/parking/entry endpoint
     */
    @Test
    void testEntryEndpoint_Success() throws Exception {
        // Arrange
        EntryRequest request = new EntryRequest("MH12AB1234", VehicleType.CAR);

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("MH12AB1234");
        vehicle.setVehicleType(VehicleType.CAR);

        ParkingSpot spot = new ParkingSpot();
        spot.setId(101L);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setVehicle(vehicle);
        ticket.setSpot(spot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);

        when(parkingService.handleVehicleEntry(any(Vehicle.class))).thenReturn(ticket);

        // Act + Assert
        mockMvc.perform(post("/api/parking/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketId", is(1)))
                .andExpect(jsonPath("$.spotId", is(101)))
                .andExpect(jsonPath("$.licensePlate", is("MH12AB1234")))
                .andExpect(jsonPath("$.status", is("ACTIVE")));

        verify(parkingService, times(1)).handleVehicleEntry(any(Vehicle.class));
    }

    /**
     * Test /api/parking/exit endpoint
     */
    @Test
    void testExitEndpoint_Success() throws Exception {
        ExitRequest request = new ExitRequest(10L);

        Payment payment = new Payment();
        payment.setId(55L);
        payment.setAmount(120.0);
        payment.setTimestamp(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);

        when(parkingService.handleVehicleExit(10L)).thenReturn(payment);

        mockMvc.perform(post("/api/parking/exit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId", is(55)))
                .andExpect(jsonPath("$.amount", is(120.0)))
                .andExpect(jsonPath("$.status", is("COMPLETED")));

        verify(parkingService, times(1)).handleVehicleExit(10L);
    }

    /**
     * Test GET /api/parking/tickets/{id} when found
     */
    @Test
    void testGetTicketById_Found() throws Exception {
        ParkingSpot spot = new ParkingSpot();
        spot.setId(202L);

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("MH09XY9999");

        Ticket ticket = new Ticket();
        ticket.setId(99L);
        ticket.setVehicle(vehicle);
        ticket.setSpot(spot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);

        when(parkingService.getTicketById(99L)).thenReturn(Optional.of(ticket));

        mockMvc.perform(get("/api/parking/tickets/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketId", is(99)))
                .andExpect(jsonPath("$.spotId", is(202)))
                .andExpect(jsonPath("$.licensePlate", is("MH09XY9999")))
                .andExpect(jsonPath("$.status", is("ACTIVE")));

        verify(parkingService, times(1)).getTicketById(99L);
    }

    /**
     * Test GET /api/parking/tickets/{id} when not found
     */
    @Test
    void testGetTicketById_NotFound() throws Exception {
        when(parkingService.getTicketById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/parking/tickets/{id}", 999L))
                .andExpect(status().isNotFound());

        verify(parkingService, times(1)).getTicketById(999L);
    }

    /**
     * Test /api/parking/entry when service throws exception
     */
    @Test
    void testEntryEndpoint_NoSpotAvailable() throws Exception {
        EntryRequest request = new EntryRequest("MH11AA0000", VehicleType.CAR);

        when(parkingService.handleVehicleEntry(any(Vehicle.class)))
                .thenThrow(new RuntimeException("No free spot available"));

        mockMvc.perform(post("/api/parking/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error", is("No free spot available")))
                .andExpect(jsonPath("$.status", is(500)))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

}
