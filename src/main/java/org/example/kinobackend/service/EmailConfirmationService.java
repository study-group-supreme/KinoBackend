package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Seat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EmailConfirmationService {
    private final JavaMailSender mailSender;

    public EmailConfirmationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmation(String email, Reservation reservation, Seat seat) {
        SimpleMailMessage confirmation = new SimpleMailMessage();
        confirmation.setTo(email);
        confirmation.setSubject("Order confirmation from KinoEk");
        confirmation.setText(
                "Dette er din ordre-bekræftelse.\n\n" +
                        "Reservationsnummer: " + reservation.getId() + "\n" +
                        "Sæde: Række " + seat.getSeatRow() +
                        ", nummer " + seat.getSeatNumber() + "\n\n" +
                        "Tak fordi du valgte KinoEk!"
        );

        mailSender.send(confirmation);
    }
}
