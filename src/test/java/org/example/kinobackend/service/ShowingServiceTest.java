package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
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
    @Mock
    TheatreRepository theatreRepository;
    @InjectMocks ShowingService showingService;
    @InjectMocks MovieService movieService;
    @InjectMocks TheatreService theatreService;
    @BeforeEach
    void setup(){
    }
    @Test
    public void createShowing_CreatesShowing(){
        Movie movie = new Movie();
        movie.setName("Test");
        movie.setId(1);

        Theatre theatre = new Theatre();
        theatre.setName("testTheatre");
        theatre.setId(1);

        Showing showing = new Showing();
        showing.setId(1);
        showing.setMovie(movie);
        showing.setTheatre(theatre);
        showing.setReservations(null);
        showing.setStartTime(null);

        when(showingRepository.save(showing)).thenReturn(showing);
        Showing result = showingService.createShowing(showing);
        assertEquals(showing, result);
        verify(showingRepository).save(showing);
    }
}
