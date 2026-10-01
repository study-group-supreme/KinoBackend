package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre createTheatre(Theatre theatre) throws IllegalArgumentException {
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new IllegalArgumentException("Theatre must be named");
        }
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(int id) {
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theatre not found"));

        theatreRepository.delete(theatre);
    }
}
