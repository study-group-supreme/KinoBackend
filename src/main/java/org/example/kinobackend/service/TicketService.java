package org.example.kinobackend.service;

import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.repository.TicketRepository;
import org.springframework.stereotype.Service;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public Ticket createTicket (Ticket ticket) throws IllegalArgumentException {
        return ticket;

    }


}
