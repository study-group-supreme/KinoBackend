package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Ticket {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_type", referencedColumnName = "id")
    private TicketType ticketType;

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
        return id == ticket.id && Objects.equals(ticketType, ticket.ticketType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketType);
    }
}
