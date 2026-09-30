package org.example.kinobackend.service;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;

public class TheatreService {
    public TheatreRepository theatreRepository;


    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);

    }


}
