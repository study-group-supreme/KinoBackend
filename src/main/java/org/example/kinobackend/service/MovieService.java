package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Movie deactivateMovie(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Movie not found with id: " + id));
        movie.setActive(false);
        return movieRepository.save(movie);
    }

}
