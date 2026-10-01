package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {


    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    Movie hobbit;
    Movie greenMile;
    List<Movie> movies;

    @BeforeEach
    void setup() {
        hobbit = new Movie();
        hobbit.setName("The Fellowship of the Ring");
        hobbit.setRuntimeMinutes(178);
        hobbit.setDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the One Ring.");
        hobbit.setPosterUrl("https://example.com");
        hobbit.setAgeLimit(11);
        hobbit.setActive(true);


        greenMile = new Movie();
        greenMile.setName("The Green Mile");
        greenMile.setRuntimeMinutes(189);
        greenMile.setDescription("A death row head guard discovers that one of his inmates has a miraculous, supernatural gift.");
        greenMile.setPosterUrl("https://example.com");
        greenMile.setAgeLimit(15);
        greenMile.setActive(false);

        movies = List.of(hobbit, greenMile);
    }

    // --- GetAllMovies ---
    @Test
    void getAllMovies_shouldReturnAllMovies() {
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
        assertTrue(result.contains(hobbit));
        assertTrue(result.contains(greenMile));
        verify(movieRepository).findAll();
    }

    @Test
    void getAllMovies_ShouldReturnEmptyList_WhenNoMovies() {
        when(movieRepository.findAll()).thenReturn(List.of());

        List<Movie> result = movieService.getAllMovies();

        assertTrue(result.isEmpty());
    }

    // --- GetMovieByName ---
    @Test
    void getMovieByName_ShouldReturnMatchingMovie() {
        when(movieRepository.findAllByName(hobbit.getName())).thenReturn(List.of(hobbit));

        List<Movie> result = movieService.getMovieByName("The Fellowship of the Ring");

        assertEquals(1, result.size());
        assertEquals("The Fellowship of the Ring", result.get(0).getName());
        verify(movieRepository).findAllByName("The Fellowship of the Ring");
    }

    @Test
    void getMovieByName_ShouldReturnEmptyList_WhenNoMatch() {
        when(movieRepository.findAllByName("Unknown Title")).thenReturn(List.of());

        List<Movie> result = movieService.getMovieByName("Unknown Title");

        assertTrue(result.isEmpty());
    }

    // --- GetActiveMovies ---
    @Test
    void getAllActiveMovies_ShouldReturnListOfActiveMovies() {
        when(movieRepository.findByIsActiveTrue()).thenReturn(List.of(hobbit));

        List<Movie> result = movieService.getAllActiveMovies();

        assertEquals(1, result.size());
        assertEquals("The Fellowship of the Ring", result.get(0).getName());
        verify(movieRepository).findByIsActiveTrue();
    }

    @Test
    void getAllActiveMovies_ShouldReturnEmptyList_WhenNoneActive() {
        when(movieRepository.findByIsActiveTrue()).thenReturn(List.of());

        assertTrue(movieService.getAllActiveMovies().isEmpty());
    }

    // --- GetAllInactiveMovies ---
    @Test
    void getAllInactiveMovies_ShouldReturnListOfInactiveMovies() {
        when(movieRepository.findByIsActiveFalse()).thenReturn(List.of(greenMile));

        List<Movie> result = movieService.getAllInactiveMovies();

        assertEquals(1, result.size());
        assertEquals("The Green Mile", result.get(0).getName());
        assertFalse(result.get(0).isActive());
        verify(movieRepository).findByIsActiveFalse();
    }

    @Test
    void getAllInactiveMovies_shouldReturnEmptyList_whenNoneInactive() {
        when(movieRepository.findByIsActiveFalse()).thenReturn(List.of());

        assertTrue(movieService.getAllInactiveMovies().isEmpty());
    }

    // --- CreateMovie ---
    @Test
    void postMovie_shouldCreateNewMovie_whenSaveIsCalled() {
        Movie happyRabbit = new Movie();
        happyRabbit.setName("The Happy Rabbit");
        happyRabbit.setRuntimeMinutes(67);
        happyRabbit.setDescription("A story about the happy rabbit, who left his home to go explore the wilderness with nothing but a semi-automatic assault rifle");
        happyRabbit.setPosterUrl("https://example.com");
        happyRabbit.setAgeLimit(18);
        happyRabbit.setActive(true);

        when(movieRepository.save(happyRabbit)).thenReturn(happyRabbit);
        Movie result = movieService.createMovie(happyRabbit);

        assertEquals(happyRabbit, result);
        verify(movieRepository).save(happyRabbit);
    }

    @Test
    void

}
