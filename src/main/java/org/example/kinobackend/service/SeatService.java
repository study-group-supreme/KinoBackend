package org.example.kinobackend.service;
import org.example.kinobackend.dto.SeatResponse;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.repository.SeatRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.example.kinobackend.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final ShowingRepository showingRepository;
    private final TicketRepository ticketRepository;

    public SeatService(SeatRepository seatRepository,
                       ShowingRepository showingRepository,
                       TicketRepository ticketRepository) {
        this.seatRepository = seatRepository;
        this.showingRepository = showingRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<SeatResponse> getSeatsForShowing(int showingId) {
        Showing showing = showingRepository.findById(showingId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Showing not found"));

        Set<Integer> takenIds = ticketRepository.findByReservationShowingId(showingId)
                .stream()
                .map(ticket -> ticket.getSeat().getId())
                .collect(Collectors.toSet());

        return seatRepository.findByTheatreId(showing.getTheatre().getId())
                .stream()
                .map(seat -> new SeatResponse(
                        seat.getId(),
                        seat.getSeatRow(),
                        seat.getSeatNumber(),
                        takenIds.contains(seat.getId())))
                .toList();
    }
}