package org.example.kinobackend.repository;

import org.example.kinobackend.model.Theatre;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {


}
