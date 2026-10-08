package org.example.kinobackend.controller;

import org.example.kinobackend.dto.MovieResponse;
import org.example.kinobackend.model.Category;
import org.example.kinobackend.dto.ShowingResponse;
import org.example.kinobackend.model.Movie;
import org.example.kinobackend.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public List<MovieResponse> getMovieByName(@PathVariable String name) {
        return movieService.getMovieByName(name);
    }

    @GetMapping("/available")
    public List<MovieResponse> getAllAvailableMovies() {
        return movieService.getAllActiveMovies();
    }

    @GetMapping("/unavailable")
    public List<MovieResponse> getAllUnavailableMovies() {
        return movieService.getAllInactiveMovies();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return movieService.getAllCategories();
    }

    @GetMapping("/categories/{category}")
    public List<MovieResponse> getMoviesByCategories(@PathVariable String category) {
        return movieService.getMoviesByCategory(category);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Movie> putMovie(@PathVariable int id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(movie, id);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> postMovie(@RequestBody Movie movie) {
        Movie saveMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(saveMovie, HttpStatus.CREATED);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateMovieById(@PathVariable int id) {
        movieService.deactivateMovieById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable int id) {
        try {
            movieService.deleteMovieById(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }


}
