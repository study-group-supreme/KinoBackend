package org.example.kinobackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonManagedReference("theatre-seats")
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "theatre")
    @JsonManagedReference("theatre-showing")
    private Set<Showing> showings = new HashSet<>();

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

    public Set<Showing> getShowings() {
        return showings;
    }

    public void setShowings(Set<Showing> showings) {
        this.showings = showings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return id == theatre.id && Objects.equals(name, theatre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
