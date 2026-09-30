package org.example.kinobackend.service;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovieByName(String name) {
        return movieRepository.findAllByName(name);
    }

    public List<Movie> getAllActiveMovies(){
        return movieRepository.findByIsActiveTrue();
    }
}
