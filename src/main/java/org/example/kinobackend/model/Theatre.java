package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Theatre {
    @Id
    private int id;
    private String name;

    private Set<Seat> seats = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return id == theatre.id && Objects.equals(name, theatre.name) && Objects.equals(seats, theatre.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, seats);
    }
}
