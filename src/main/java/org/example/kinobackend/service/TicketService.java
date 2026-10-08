package org.example.kinobackend.service;

import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.repository.TicketRepository;
import org.example.kinobackend.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> findTicketsByShowingId(int showingId){
        List<Ticket> ticketIds = new ArrayList<>();
        for (Ticket tickets : ticketRepository.findByReservationShowingId(showingId)){
            ticketIds.add(tickets);
        }
        return ticketIds;
    }
}
