package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.dto.ShowingByDateResponse;
import org.example.kinobackend.dto.ShowingResponse;
import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.MovieRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    private final MovieRepository movieRepository;

    public ShowingService(ShowingRepository showingRepository, MovieRepository movieRepository) {
        this.showingRepository = showingRepository;
        this.movieRepository = movieRepository;
    }

    public Showing createShowing(Showing showing) {
        if (showing.getMovie() == null) {
            throw new IllegalArgumentException("Showing cant be created without a movie");
        }
        if (showing.getTheatre() == null) {
            throw new IllegalArgumentException("Showing cant be created without a theatre");
        }
        Movie movie = movieRepository.findById(showing.getMovie().getId()).orElseThrow();
        checkForShowingInTheaterOverlap(showing, movie, 0);
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
    public Showing updateShowing(Showing showing, int id){
        if(showing.getMovie() == null){
            throw new IllegalArgumentException("A movie must be tied to a showing");
        }
        if(showing.getTheatre() == null){
            throw new IllegalArgumentException("A theatre must be tied to a showing");
        }
        if(showing.getStartTime() == null){
            throw new IllegalArgumentException("A showing must have a start time");
        }
        Showing existing = showingRepository.findById(id)
                .orElseThrow();

        Movie movie = movieRepository.findById(showing.getMovie().getId()).orElseThrow();
        checkForShowingInTheaterOverlap(showing, movie, id);

        existing.setMovie(showing.getMovie());
        existing.setStartTime(showing.getStartTime());
        existing.setTheatre(showing.getTheatre());
        return showingRepository.save(existing);
    }

    private void checkForShowingInTheaterOverlap(Showing showing, Movie movie, int ignoreId){
        LocalDateTime newStart = showing.getStartTime();
        LocalDateTime newEnd = newStart.plusMinutes(movie.getRuntimeMinutes()).plusMinutes(15);

        for (Showing existing : showingRepository.findByTheatreId(showing.getTheatre().getId())){
            if(existing.getId() == ignoreId) continue;

            LocalDateTime existingStart = existing.getStartTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(existing.getMovie().getRuntimeMinutes()).plusMinutes(15);

            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,"The theatre is already booked at that time");
            }
        }
    }

}
