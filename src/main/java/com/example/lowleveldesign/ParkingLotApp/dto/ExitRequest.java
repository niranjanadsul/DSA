package com.example.lowleveldesign.ParkingLotApp.dto;

public class ExitRequest {
    private Long ticketId;

    public ExitRequest() {}

    public ExitRequest(Long ticketId) { this.ticketId = ticketId; }

    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
}
