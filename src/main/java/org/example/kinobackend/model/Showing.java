package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Showing {
    @Id
    private int id;
    private LocalDateTime startTime;
    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "id")
    private Movie movie;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return id == showing.id && Objects.equals(startTime, showing.startTime) && Objects.equals(movie, showing.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, movie);
    }
}
