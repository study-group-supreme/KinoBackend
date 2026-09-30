package org.example.kinobackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Showing {
    @Id
    private int id;
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "id")
    private Movie movie;

    @OneToMany(mappedBy = "showing")
    @JsonBackReference
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "theatre", referencedColumnName = "id")
    private Theatre theatre;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return id == showing.id && Objects.equals(startTime, showing.startTime) && Objects.equals(movie, showing.movie) && Objects.equals(theatre, showing.theatre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, movie, theatre);
    }
}
