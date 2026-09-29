package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Theatre {
    @Id
    private int id;
    private String theatreName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theaterName) {
        this.theatreName = theaterName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return id == theatre.id && Objects.equals(theatreName, theatre.theatreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theatreName);
    }
}
