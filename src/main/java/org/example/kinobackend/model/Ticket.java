package org.example.kinobackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_type", referencedColumnName = "id")
    @JsonBackReference
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "seat", referencedColumnName = "id")
    @JsonBackReference("seat-tickets")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    @JsonBackReference("reservation-tickets")
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(ticketType, ticket.ticketType) && Objects.equals(seat, ticket.seat) && Objects.equals(reservation, ticket.reservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketType, seat, reservation);
    }
}
