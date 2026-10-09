package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.dto.MovieResponse;
import org.example.kinobackend.dto.ShowingResponse;
import org.example.kinobackend.model.Category;
import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.repository.CategoryRepository;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final CategoryRepository categoryRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
    }

    public List<MovieResponse> getMovieByName(String name) {
        List<Movie> movies = movieRepository.findAllByName(name);
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
    }

    public List<MovieResponse> getAllActiveMovies() {
        List<Movie> movies = movieRepository.findByIsActiveTrue();
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
    }

    public List<MovieResponse> getAllInactiveMovies() {
        List<Movie> movies = movieRepository.findByIsActiveFalse();
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
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

    public Movie getMovieById(int id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }

    public Movie updateMovie(Movie movie, int id) {
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new IllegalArgumentException("Movie must have a name");
        }
        if (movie.getDescription() == null || movie.getDescription().isBlank()) {
            throw new IllegalArgumentException("Movie must have a description");
        }
        if (movie.getRuntimeMinutes() < 0) {
            throw new IllegalArgumentException("Runtime must be a positive number");
        }

        Movie existing = movieRepository.findById(id)
                .orElseThrow(); //When we agree on custom exceptions I can fill this out, but it will still work now
        if (existing.isActive() && !movie.isActive() && !existing.getShowings().isEmpty()){
            throw new IllegalArgumentException("Cannot set movie to inactive because it still has showings");
        }
        existing.setName(movie.getName());
        existing.setDescription(movie.getDescription());
        existing.setRuntimeMinutes(movie.getRuntimeMinutes());
        existing.setActive(movie.isActive());
        existing.setAgeLimit(movie.getAgeLimit());
        existing.setPosterUrl(movie.getPosterUrl());
        return movieRepository.save(existing);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<MovieResponse> getMoviesByCategory(String category) {
        List<Movie> movies = movieRepository.findByCategories_NameAndIsActiveTrue(category);


        if (movies.isEmpty())
            throw new EntityNotFoundException(
                    "No active movies found in category: " + category
            );

        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
    }

    private MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getName(),
                movie.getRuntimeMinutes(),
                movie.getDescription(),
                movie.getPosterUrl(),
                movie.getAgeLimit(),
                movie.isActive(),
                movie.getCategories()
        );
    }

    public Movie getMovieByShowingId(int showingId) {
        return movieRepository.findMovieByShowingsId(showingId);
    }
}
