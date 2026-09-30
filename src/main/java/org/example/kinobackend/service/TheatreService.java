package org.example.kinobackend.service;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;


    public Theatre createTheatre(Theatre theatre) throws IllegalArgumentException {
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new IllegalArgumentException("Theatre must be named");
        }
        return theatreRepository.save(theatre);

    }


}
