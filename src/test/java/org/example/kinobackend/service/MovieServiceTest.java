package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Movie m1 = new Movie();
        m1.setName("The Fellowship of the Ring");
        m1.setRuntimeMinutes(178);
        m1.setDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the One Ring.");
        m1.setPosterUrl("https://example.com");
        m1.setAgeLimit(11);
        m1.setActive(true);


        Movie m2 = new Movie();
        m2.setName("The Green Mile");
        m2.setRuntimeMinutes(189);
        m2.setDescription("A death row head guard discovers that one of his inmates has a miraculous, supernatural gift.");
        m2.setPosterUrl("https://example.com");
        m2.setAgeLimit(15);
        m2.setActive(true);

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