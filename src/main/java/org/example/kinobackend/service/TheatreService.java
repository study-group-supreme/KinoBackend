package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Theatre updateTheatre(int id, Theatre theatre) {
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new IllegalArgumentException("Theatre name must not be blank");
        }
        Theatre existing = theatreRepository.findById(id)
                .orElseThrow(); //When we agree on custom exceptions i can fill this out, but it will still work now
        existing.setName(theatre.getName());
        return theatreRepository.save(existing);

    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public void deleteTheatre(int id) {
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theatre not found"));

        theatreRepository.delete(theatre);
    }
    public Theatre getTheatreById(int id){
        Theatre specific = theatreRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Theatre not found"));
        return specific;
    }
}
