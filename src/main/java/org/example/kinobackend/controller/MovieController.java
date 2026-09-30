package org.example.kinobackend.controller;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{name}")
    public List<Movie> getMovieByName (@PathVariable String name) {
        return movieService.getMovieByName(name);
    }

    @GetMapping("/available")
    public List<Movie> getAllAvailableMovies(){
        return movieService.getAllActiveMovies();
    }

}
