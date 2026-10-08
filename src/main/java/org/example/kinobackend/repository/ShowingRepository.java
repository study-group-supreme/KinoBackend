package org.example.kinobackend.repository;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {

    int movie(Movie movie);

    List<Showing> findByMovieIdAndStartTimeBetweenOrderByStartTimeAsc(int movieId, LocalDateTime from, LocalDateTime to);
    List<Showing> findShowingsByMovieId(int movieId);
}
