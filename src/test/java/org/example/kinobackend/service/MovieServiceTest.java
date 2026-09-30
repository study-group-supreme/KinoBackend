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
        Movie hobbit = new Movie();
        hobbit.setName("The Fellowship of the Ring");
        hobbit.setRuntimeMinutes(178);
        hobbit.setDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the One Ring.");
        hobbit.setPosterUrl("https://example.com");
        hobbit.setAgeLimit(11);
        hobbit.setActive(true);


        Movie greenMile = new Movie();
        greenMile.setName("The Green Mile");
        greenMile.setRuntimeMinutes(189);
        greenMile.setDescription("A death row head guard discovers that one of his inmates has a miraculous, supernatural gift.");
        greenMile.setPosterUrl("https://example.com");
        greenMile.setAgeLimit(15);
        greenMile.setActive(true);

        movies = List.of(hobbit, greenMile);
    }


    @Test
    void getAllMovies() {
    }

    @Test
    void getMovieByName() {
    }

    @Test
    void getAllActiveMovies() {
    }

    @Test
    void getAllInactiveMovies() {
    }
}