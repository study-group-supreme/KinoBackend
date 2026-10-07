
package org.example.kinobackend.service;

import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.repository.TicketRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationService {
    private final JavaMailSender mailSender;
    private final TicketRepository ticketRepository;

    public EmailConfirmationService(JavaMailSender mailSender, TicketRepository ticketRepository) {
        this.mailSender = mailSender;
        this.ticketRepository = ticketRepository;
    }

    public void sendConfirmation(String email, Reservation reservation) {
        String seatsAndType = "";

        for (Ticket ticket : reservation.getTickets()) {
            String typeName = "Ikke valgt";
            if (ticket.getTicketType() != null) {
                typeName = ticket.getTicketType().getName();
            }

            seatsAndType += "Række " + ticket.getSeat().getSeatRow()
                    + ", nummer " + ticket.getSeat().getSeatNumber()
                    + ", billettype: " + typeName + "\n";
        }

        SimpleMailMessage confirmation = new SimpleMailMessage();
        confirmation.setTo(email);
        confirmation.setSubject("Order confirmation from KinoEk");
        confirmation.setText(
                "Dette er din ordre-bekræftelse.\n\n" +
                        "Reservationsnummer: " + reservation.getId() + "\n\n" +
                        "Dine billetter:\n" + seatsAndType + "\n" +
                        "Tak fordi du valgte KinoEk!"
        );

        mailSender.send(confirmation);
    }
}
