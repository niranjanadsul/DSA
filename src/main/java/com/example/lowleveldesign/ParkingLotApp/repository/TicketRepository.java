package com.example.lowleveldesign.ParkingLotApp.repository;

import com.example.lowleveldesign.ParkingLotApp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
