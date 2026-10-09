package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Reservation;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.MovieRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.example.kinobackend.repository.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowingServiceTest {
    @Mock
    ShowingRepository showingRepository;
    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    ShowingService showingService;
    @InjectMocks
    Showing showing;
    @InjectMocks
    Movie movie;
    @InjectMocks
    Theatre theatre;


    @BeforeEach
    void setup() {
        movie = new Movie();
        movie.setName("test");
        movie.setId(1);

        theatre = new Theatre();
        theatre.setId(1);
        theatre.setName("test");

        showing = new Showing();
        showing.setId(1);
        showing.setMovie(movie);
        showing.setTheatre(theatre);
        showing.setStartTime(LocalDateTime.of(2026, 10, 10, 18, 0));
        showing.setReservations(new HashSet<>());

    }

    @Test
    public void createShowing_CreatesShowing() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(showingRepository.save(showing)).thenReturn(showing);
        Showing result = showingService.createShowing(showing);
        assertEquals(showing, result);
        verify(showingRepository).save(showing);
    }

    @Test
    public void deleteShowing_WithNoReservations_DeletesShowing() {
        when(showingRepository.findById(1)).thenReturn(Optional.of(showing));
        showingService.deleteShowing(1);
        verify(showingRepository).delete(showing);
    }

    @Test
    public void deleteShowing_WithReservations_ReturnsIllegalArgumentException() {
        Reservation reservation = new Reservation();
        reservation.setShowing(showing);
        showing.getReservations().add(reservation);
        when(showingRepository.findById(1)).thenReturn(Optional.of(showing));

        assertThrows(IllegalArgumentException.class, () -> showingService.deleteShowing(1));

    }
}
