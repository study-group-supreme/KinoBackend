
 package org.example.kinobackend.service;

import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Ticket;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

    @Service
    public class EmailConfirmationService {
        private final JavaMailSender mailSender;

        public EmailConfirmationService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void sendConfirmation(String email, Reservation reservation) {
            String seats = "";
            for (Ticket ticket : reservation.getTickets()) {
                seats += "Række " + ticket.getSeat().getSeatRow()
                        + ", nummer " + ticket.getSeat().getSeatNumber() + "\n";
            }

            SimpleMailMessage confirmation = new SimpleMailMessage();
            confirmation.setTo(email);
            confirmation.setSubject("Order confirmation from KinoEk");
            confirmation.setText(
                    "Dette er din ordre-bekræftelse.\n\n" +
                            "Reservationsnummer: " + reservation.getId() + "\n\n" +
                            "Dine sæder:\n" + seats + "\n" +
                            "Tak fordi du valgte KinoEk!"
            );

            mailSender.send(confirmation);
        }
    }

