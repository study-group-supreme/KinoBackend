package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Movie {
    @Id
    private int id;
    private String name;
    private int runtimeMinutes;
    private String description;
    private String poserUrl;
    private int ageLimit;
    private boolean isActive;

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

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoserUrl() {
        return poserUrl;
    }

    public void setPoserUrl(String poserUrl) {
        this.poserUrl = poserUrl;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean is_active) {
        this.isActive = is_active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && runtimeMinutes == movie.runtimeMinutes && ageLimit == movie.ageLimit && isActive == movie.isActive && Objects.equals(name, movie.name) && Objects.equals(description, movie.description) && Objects.equals(poserUrl, movie.poserUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, runtimeMinutes, description, poserUrl, ageLimit, isActive);
    }
}
