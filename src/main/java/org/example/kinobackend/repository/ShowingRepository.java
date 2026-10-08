package org.example.kinobackend.repository;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {

    List<Showing> findByMovieIdAndStartTimeAfterOrderByStartTimeAsc(int movieId, LocalDateTime time);

    int movie(Movie movie);
    List<Showing> findShowingsByMovieId(int movieId);
}
