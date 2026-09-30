package org.example.kinobackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Seat {
    @Id
    private int id;
    private int seatRow;
    private int seatNumber;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "theatre", referencedColumnName = "id")
    private Theatre theatre;

    @OneToMany(mappedBy = "seat")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id && seatRow == seat.seatRow && seatNumber == seat.seatNumber && isAvailable == seat.isAvailable && Objects.equals(theatre, seat.theatre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatRow, seatNumber, isAvailable, theatre);
    }
}
