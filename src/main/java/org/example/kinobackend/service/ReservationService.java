package org.example.kinobackend.service;

import org.example.kinobackend.model.*;
import org.example.kinobackend.repository.ReservationRepository;
import org.example.kinobackend.repository.SeatRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.example.kinobackend.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShowingRepository showingRepository;
    private final SeatRepository seatRepository;
    private final EmailConfirmationService emailConfirmationService;
    private final TicketTypeRepository ticketTypeRepository;

    public ReservationService(ReservationRepository reservationRepository, ShowingRepository showingRepository, SeatRepository seatRepository, EmailConfirmationService emailConfirmationService, TicketTypeRepository ticketTypeRepository) {
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.emailConfirmationService = emailConfirmationService;
        this.ticketTypeRepository = ticketTypeRepository;
    }


    public Reservation createReservation(Reservation reservation, int showingId,
                                         List<Integer> seatIds, int ticketTypeId) {
        reservation.setShowing(showingRepository.findById(showingId).orElseThrow());
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId).orElseThrow();

        for (int seatId : seatIds) {
            Ticket ticket = new Ticket();
            ticket.setSeat(seatRepository.findById(seatId).orElseThrow());
            ticket.setTicketType(ticketType);
            ticket.setReservation(reservation);
            reservation.getTickets().add(ticket);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        emailConfirmationService.sendConfirmation(
                savedReservation.getCustomerMail(), savedReservation);

        return savedReservation;
    }
}