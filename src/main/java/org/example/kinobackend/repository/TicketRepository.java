package org.example.kinobackend.repository;


import org.example.kinobackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByReservationShowingId(int showingId);}