package org.example.kinobackend.controller;

import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{showingId}/{seatId}")
    public ResponseEntity<Reservation> postReservation(@PathVariable int showingId, @PathVariable int seatId, @RequestBody Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation, showingId, seatId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }}
