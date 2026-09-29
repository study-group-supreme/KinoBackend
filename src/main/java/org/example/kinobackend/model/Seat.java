package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Seat {
    @Id
    private int id;
    private int seatRow;
    private int seatNumber;
    private boolean isAvaliable;

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

    public boolean isAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id && seatRow == seat.seatRow && seatNumber == seat.seatNumber && isAvaliable == seat.isAvaliable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatRow, seatNumber, isAvaliable);
    }
}
