package org.example.kinobackend.service;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreService {

    private TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre createTheatre(Theatre theatre) throws IllegalArgumentException {
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new IllegalArgumentException("Theatre must be named");
        }
        return theatreRepository.save(theatre);
    }

    public Theatre updateTheatre(int id, Theatre theatre) {
        Theatre existing = theatreRepository.findById(id)
                .orElseThrow();
        if(theatre.getName() == null || theatre.getName().isBlank()){
            throw new IllegalArgumentException("Theatre name must not be blank");
        }
        existing.setName(theatre.getName());
        return theatreRepository.save(existing);

    }


}
