package org.example.kinobackend.config;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataMovie implements CommandLineRunner {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
