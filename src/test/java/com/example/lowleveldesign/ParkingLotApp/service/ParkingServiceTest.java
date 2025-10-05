package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.*;
import com.example.lowleveldesign.ParkingLotApp.model.enums.*;
import com.example.lowleveldesign.ParkingLotApp.observer.NotificationObserver;
import com.example.lowleveldesign.ParkingLotApp.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ParkingService covering entry, exit, and payment flows.
 */
public class ParkingServiceTest {

    @Mock private VehicleRepository vehicleRepository;
    @Mock private TicketRepository ticketRepository;
    @Mock private ParkingSpotRepository spotRepository;
    @Mock private PaymentRepository paymentRepository;
    @Mock private NotificationObserver observer1;
    @Mock private NotificationObserver observer2;

    private ParkingService parkingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        parkingService = new ParkingService(
                vehicleRepository,
                ticketRepository,
                spotRepository,
                paymentRepository,
                List.of(observer1, observer2)
        );
    }

    /**
     * Helper to create a ParkingSpot that matches the required SpotType for a given vehicle type.
     */
    private ParkingSpot createAvailableSpotFor(VehicleType vehicleType) {
        SpotType spotType = switch (vehicleType) {
            case BIKE -> SpotType.COMPACT;
            case CAR -> SpotType.LARGE;
            case TRUCK -> SpotType.HANDICAPPED;
        };
        ParkingSpot spot = new ParkingSpot();
        spot.setId(new Random().nextLong(1, 999));
        spot.setSpotType(spotType);
        spot.setStatus(SpotStatus.AVAILABLE);
        return spot;
    }

    /**
     * Test for successful vehicle entry and ticket creation.
     */
    @Test
    void testHandleVehicleEntry_Success() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setLicensePlate("MH12AB1234");
        vehicle.setVehicleType(VehicleType.CAR);

        ParkingSpot freeSpot = createAvailableSpotFor(vehicle.getVehicleType());

        when(spotRepository.findAll()).thenReturn(List.of(freeSpot));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Ticket ticket = parkingService.handleVehicleEntry(vehicle);

        // Assert
        assertNotNull(ticket);
        assertEquals(vehicle, ticket.getVehicle());
        assertEquals(freeSpot, ticket.getSpot());
        assertEquals(TicketStatus.ACTIVE, ticket.getStatus());
        verify(observer1, times(1)).notify(contains("entered"));
        verify(observer2, times(1)).notify(contains("entered"));
        verify(spotRepository).save(freeSpot);
    }

    /**
     * Test for vehicle exit and payment calculation.
     */
    @Test
    void testHandleVehicleExit_Success() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setId(2L);
        vehicle.setLicensePlate("MH14CD5678");
        vehicle.setVehicleType(VehicleType.BIKE);

        ParkingSpot spot = createAvailableSpotFor(vehicle.getVehicleType());
        spot.setStatus(SpotStatus.OCCUPIED);

        Ticket ticket = new Ticket();
        ticket.setId(10L);
        ticket.setVehicle(vehicle);
        ticket.setSpot(spot);
        ticket.setEntryTime(LocalDateTime.now().minusHours(3)); // 3 hours ago
        ticket.setStatus(TicketStatus.ACTIVE);

        when(ticketRepository.findById(10L)).thenReturn(Optional.of(ticket));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Payment payment = parkingService.handleVehicleExit(10L);

        // Assert
        assertNotNull(payment);
        assertTrue(payment.getAmount() > 0);
        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
        assertEquals(TicketStatus.COMPLETED, ticket.getStatus());
        assertEquals(SpotStatus.AVAILABLE, spot.getStatus());
        verify(spotRepository).save(spot);
        verify(ticketRepository).save(ticket);
        verify(paymentRepository).save(payment);
        verify(observer1).notify(contains("exited"));
        verify(observer2).notify(contains("exited"));
    }

    /**
     * Test for missing spot scenario (no available spot).
     */
    @Test
    void testHandleVehicleEntry_NoAvailableSpot() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("MH99ZZ1111");
        vehicle.setVehicleType(VehicleType.TRUCK);

        when(spotRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> parkingService.handleVehicleEntry(vehicle));

        assertEquals("No free spot available", ex.getMessage());
        verify(observer1, never()).notify(anyString());
    }

    /**
     * Test for invalid ticket on exit.
     */
    @Test
    void testHandleVehicleExit_InvalidTicket() {
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> parkingService.handleVehicleExit(999L));

        assertEquals("Ticket not found", ex.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    /**
     * Test to verify that both observers are called in the correct order.
     */
    @Test
    void verifyCallOrderAndObserverNotification() {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("MH20GH8888");
        vehicle.setVehicleType(VehicleType.CAR);

        ParkingSpot spot = createAvailableSpotFor(vehicle.getVehicleType());
        when(spotRepository.findAll()).thenReturn(List.of(spot));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(inv -> inv.getArgument(0));

        parkingService.handleVehicleEntry(vehicle);

        InOrder inOrder = inOrder(observer1, observer2);
        inOrder.verify(observer1).notify(contains("entered"));
        inOrder.verify(observer2).notify(contains("entered"));
    }
}
