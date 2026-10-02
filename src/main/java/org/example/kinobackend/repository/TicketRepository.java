package org.example.kinobackend.repository;

import org.example.kinobackend.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketType, Integer> {
}
