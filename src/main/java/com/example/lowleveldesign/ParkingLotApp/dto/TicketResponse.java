package com.example.lowleveldesign.ParkingLotApp.dto;

import com.example.lowleveldesign.ParkingLotApp.model.enums.TicketStatus;

import java.time.LocalDateTime;

public class TicketResponse {
    private Long ticketId;
    private Long spotId;
    private String licensePlate;
    private LocalDateTime entryTime;
    private TicketStatus status;

    public TicketResponse() {}

    public TicketResponse(Long ticketId, Long spotId, String licensePlate, LocalDateTime entryTime, TicketStatus status) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.status = status;
    }

    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }

    public Long getSpotId() { return spotId; }
    public void setSpotId(Long spotId) { this.spotId = spotId; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
