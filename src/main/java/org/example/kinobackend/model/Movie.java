package org.example.kinobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Movie {
    @Id
    private int id;
    private String name;
    private int runtime_minutes;
    private String description;
    private String poserUrl;
    private int age_limit;
    private boolean is_active;

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

    public int getRuntime_minutes() {
        return runtime_minutes;
    }

    public void setRuntime_minutes(int runtime_minutes) {
        this.runtime_minutes = runtime_minutes;
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

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && runtime_minutes == movie.runtime_minutes && age_limit == movie.age_limit && is_active == movie.is_active && Objects.equals(name, movie.name) && Objects.equals(description, movie.description) && Objects.equals(poserUrl, movie.poserUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, runtime_minutes, description, poserUrl, age_limit, is_active);
    }
}
