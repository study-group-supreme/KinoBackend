package org.example.kinobackend.service;

import org.example.kinobackend.model.TicketType;
import org.example.kinobackend.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;


    public TicketTypeService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public TicketType createTicketType(TicketType ticketType) throws IllegalArgumentException {

        if (ticketType.getName() == null || ticketType.getName().isBlank()) {
            throw new IllegalArgumentException("Ticket type must be named");
        }

        if (ticketType.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (ticketTypeRepository.findByName(ticketType.getName()).isPresent()) {
            throw new IllegalArgumentException("Ticket type with that name already exists");
        }

        return ticketTypeRepository.save(ticketType);
    }

    public List<TicketType> getAll() {
        return ticketTypeRepository.findAll();
    }

    public Optional<TicketType> findTicketTypeByName(String name) {
        return ticketTypeRepository.findByName(name);
    }

    public Optional<TicketType> findTicketTypeById(int id) {
        return ticketTypeRepository.findById(id);
    }

    public Optional<TicketType> updateTicketType(int id, TicketType ticketType) {
        if (ticketType.getName() == null || ticketType.getName().isBlank()) {

            throw new IllegalArgumentException("Ticket type must be named");
        }

        if (ticketType.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (ticketTypeRepository.findByName(ticketType.getName()).isPresent()) {
            throw new IllegalArgumentException("Ticket type with that name already exists");
        }

        TicketType existingTicketType = ticketTypeRepository.findById(id)
                .orElseThrow();
        existingTicketType.setName(ticketType.getName());
        existingTicketType.setPrice(ticketType.getPrice());

        return Optional.of(ticketTypeRepository.save(existingTicketType));
    }
}