package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Showing {
    @Id
    private int id;
    private LocalDateTime startTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return id == showing.id && Objects.equals(startTime, showing.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime);
    }
}
