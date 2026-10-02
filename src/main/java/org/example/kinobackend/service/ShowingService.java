package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class ShowingService {
    private ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public Showing createShowing(Showing showing) {
        if (showing.getMovie() == null) {
            throw new IllegalArgumentException("Showing cant be created without a movie");
        }
        if (showing.getTheatre() == null) {
            throw new IllegalArgumentException("Showing cant be created without a theatre");
        }
        return showingRepository.save(showing);
    }

    public void deleteShowing(int id) {
        Showing showingToBeDeleted = showingRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No showing found"));
        if (!showingToBeDeleted.getReservations().isEmpty()) {
            throw new IllegalArgumentException("You cannot delete a showing containing reservations");
        }
        showingRepository.delete(showingToBeDeleted);
    }
}
