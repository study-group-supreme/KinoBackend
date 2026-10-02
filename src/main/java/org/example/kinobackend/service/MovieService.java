package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovieByName(String name) {
        return movieRepository.findAllByName(name);
    }

    public List<Movie> getAllActiveMovies() {
        return movieRepository.findByIsActiveTrue();
    }

    public List<Movie> getAllInactiveMovies() {
        return movieRepository.findByIsActiveFalse();
    }

    public Movie createMovie(Movie movie) {
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new IllegalArgumentException("Fill out name to continue");
        }
        if (movie.getRuntimeMinutes() <= 0) {
            throw new IllegalArgumentException("Runtime must be positive");
        }
        if (movie.getDescription() == null || movie.getDescription().isBlank()) {
            throw new IllegalArgumentException("Fill out description to continue");
        }
        return movieRepository.save(movie);
    }


    public Movie deactivateMovieById(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Movie not found with id: " + id));
        movie.setActive(false);
        return movieRepository.save(movie);
    }

    public void deleteMovieById(int id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + id));
        if (!movie.getShowings().isEmpty()) {
            throw new IllegalStateException("Cannot delete movie with id: " + id + " because it has existing showings");
        }
        movieRepository.delete(movie);
    }


}
