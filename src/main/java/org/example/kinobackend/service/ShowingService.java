package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.dto.ShowingByDateResponse;
import org.example.kinobackend.dto.ShowingResponse;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class ShowingService {
    private final ShowingRepository showingRepository;

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

    public Showing getShowingById(int id) {
        Showing specific = showingRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Showing not found"));
        return specific;
    }

    private ShowingResponse toResponse(Showing showing) {
        return new ShowingResponse(
                showing.getId(),
                showing.getStartTime(),
                showing.getMovie().getId(),
                showing.getMovie().getName(),
                showing.getTheatre().getId(),
                showing.getTheatre().getName()
        );
    }

    public ShowingResponse getShowingResponseById(int id) {
        return toResponse(getShowingById(id));
    }


    public List<ShowingResponse> getUpcomingShowingsForMovieBetweenTimes(int movieId) {
        LocalDateTime now = LocalDateTime.now();
        List<Showing> showings = showingRepository.findByMovieIdAndStartTimeBetweenOrderByStartTimeAsc(
                movieId, now, now.plusMonths(3));
        List<ShowingResponse> responses = new ArrayList<>();
        for (Showing showing : showings) {
            responses.add(toResponse(showing));
        }
        return responses;
    }

    public List<ShowingResponse> getAllShowings() {
        List<Showing> showings = showingRepository.findAll();
        List<ShowingResponse> responses = new ArrayList<>();
        for (Showing showing : showings) {
            responses.add(toResponse(showing));
        }
        return responses;
    }
    public List<Showing> getShowingsByMovieId(int movieId){
        return showingRepository.findShowingsByMovieId(movieId);
    }

    public List<ShowingByDateResponse> getUpcomingShowingsGrouped(int movieId) {
        List<ShowingResponse> flatList = getUpcomingShowingsForMovieBetweenTimes(movieId);

        Map<String, List<ShowingResponse>> grouped = new TreeMap<>();

        DateTimeFormatter european = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (ShowingResponse s : flatList) {
            String date = s.startTime().toLocalDate().format(european);

            if (!grouped.containsKey(date)) {
                grouped.put(date, new ArrayList<>());
            }

            grouped.get(date).add(s);
        }

        List<ShowingByDateResponse> result = new ArrayList<>();

        for (Map.Entry<String, List<ShowingResponse>> entry : grouped.entrySet()) {
            String date = entry.getKey();
            List<ShowingResponse> showings = entry.getValue();

            result.add(new ShowingByDateResponse(date, showings));
        }
        return result;
    }
}
