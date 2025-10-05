package com.example.lowleveldesign.ParkingLotApp.service;

import com.example.lowleveldesign.ParkingLotApp.model.Ticket;
import com.example.lowleveldesign.ParkingLotApp.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Handles creation and management of Parking Tickets.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
