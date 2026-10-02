package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovieByName(String name) {
        return movieRepository.findAllByName(name);
    }

    public List<Movie> getAllActiveMovies(){
        return movieRepository.findByIsActiveTrue();
    }

    public List<Movie> getAllInactiveMovies(){
        return movieRepository.findByIsActiveFalse();
    }

    public Optional<Movie> getMovieById(int id){
        return movieRepository.findById(id);
    }

    public Movie updateMovie(Movie movie, int id){
        if (movie.getName() == null || movie.getName().isBlank()){
            throw new IllegalArgumentException("Movie must have a name");
        }
        if (movie.getDescription() == null || movie.getDescription().isBlank()){
            throw new IllegalArgumentException("Movie must have a description");
        }
        if(movie.getRuntimeMinutes() < 0){
            throw new IllegalArgumentException("Runtime must be a positive number");
        }

        Movie existing = movieRepository.findById(id)
                .orElseThrow(); //When we agree on custom exceptions I can fill this out, but it will still work now
        existing.setName(movie.getName());
        existing.setDescription(movie.getDescription());
        existing.setRuntimeMinutes(movie.getRuntimeMinutes());
        existing.setActive(movie.isActive());
        existing.setAgeLimit(movie.getAgeLimit());
        existing.setPosterUrl(movie.getPosterUrl());
        existing.setCategories(movie.getCategories());
        existing.setShowings(movie.getShowings());
        return movieRepository.save(existing);
    }
}
