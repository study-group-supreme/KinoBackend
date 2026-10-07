package org.example.kinobackend.controller;

import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Seat;
import org.example.kinobackend.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{showingId}")
    public ResponseEntity<Reservation> postReservation(@PathVariable int showingId, @RequestParam List<Integer> seatIds, @RequestBody Reservation reservation, @RequestParam int ticketTypeId) {
        Reservation created = reservationService.createReservation(reservation, showingId, seatIds, ticketTypeId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }}
