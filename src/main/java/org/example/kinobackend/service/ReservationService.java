package org.example.kinobackend.service;

import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Seat;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.repository.ReservationRepository;
import org.example.kinobackend.repository.SeatRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShowingRepository showingRepository;
    private final SeatRepository seatRepository;
    private final EmailConfirmationService emailConfirmationService;

    public ReservationService(ReservationRepository reservationRepository, ShowingRepository showingRepository, SeatRepository seatRepository, EmailConfirmationService emailConfirmationService) {
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.emailConfirmationService = emailConfirmationService;
    }


    public Reservation createReservation(Reservation reservation, int showingId, List<Integer> seatIds) {
        reservation.setShowing(showingRepository.findById(showingId).orElseThrow());

        for (int seatId : seatIds) {
            Ticket ticket = new Ticket();
            ticket.setSeat(seatRepository.findById(seatId).orElseThrow());
            ticket.setReservation(reservation);
            reservation.getTickets().add(ticket);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        emailConfirmationService.sendConfirmation(
                savedReservation.getCustomerMail(), savedReservation);

        return savedReservation;
    }
}