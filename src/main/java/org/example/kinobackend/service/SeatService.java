package org.example.kinobackend.service;

import org.example.kinobackend.dto.SeatResponse;
import org.example.kinobackend.model.Seat;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.repository.SeatRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.example.kinobackend.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
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
        Showing showing = findShowing(showingId);
        Set<Integer> takenIds = findTakenSeatIds(showingId);

        List<SeatResponse> result = new ArrayList<>();
        for (Seat seat : seatRepository.findByTheatreId(showing.getTheatre().getId())) {
            boolean taken = takenIds.contains(seat.getId());
            result.add(new SeatResponse(seat.getId(), seat.getSeatRow(), seat.getSeatNumber(), taken));
        }
        return result;
    }

    private Showing findShowing(int showingId) {
        return showingRepository.findById(showingId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Showing not found"));
    }

    private Set<Integer> findTakenSeatIds(int showingId) {
        Set<Integer> ids = new HashSet<>();
        for (Ticket ticket : ticketRepository.findByReservationShowingId(showingId)) {
            ids.add(ticket.getSeat().getId());
        }
        return ids;
    }
}