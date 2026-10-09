package org.example.kinobackend.repository;

import org.example.kinobackend.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {

    Optional<TicketType> findByName(String name);
}
