package org.example.kinobackend.repository;

import org.example.kinobackend.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
