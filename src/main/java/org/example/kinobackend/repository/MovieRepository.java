package org.example.kinobackend.repository;

import org.example.kinobackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByName(String name);

    List<Movie> findByIsActiveTrue();

    List<Movie> findByIsActiveFalse();

    List<Movie> findByCategories_NameAndIsActiveTrue(String name);
}
