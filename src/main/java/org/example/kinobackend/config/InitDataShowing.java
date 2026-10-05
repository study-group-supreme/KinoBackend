package org.example.kinobackend.config;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.MovieRepository;
import org.example.kinobackend.repository.ShowingRepository;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class InitDataShowing implements CommandLineRunner {
    private final ShowingRepository showingRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public InitDataShowing(ShowingRepository showingRepository,
                           MovieRepository movieRepository,
                           TheatreRepository theatreRepository) {
        this.showingRepository = showingRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only create showings if there are none yet
        if (showingRepository.count() > 0) {
            return;
        }

        // Only active movies get showings
        List<Movie> activeMovies = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            if (movie.isActive()) {
                activeMovies.add(movie);
            }
        }

        List<Theatre> theatres = theatreRepository.findAll();

        if (activeMovies.isEmpty() || theatres.isEmpty()) {
            return;
        }

        int[] startHours = {12, 16, 20};
        int movieIndex = 0;
        List<Showing> showings = new ArrayList<>();

        // Showings for the next 7 days
        for (int day = 0; day < 7; day++) {
            for (Theatre theatre : theatres) {
                for (int hour : startHours) {
                    // Pick the next movie, and start over when we run out
                    Movie movie = activeMovies.get(movieIndex % activeMovies.size());
                    movieIndex++;

                    Showing showing = new Showing();
                    showing.setMovie(movie);
                    showing.setTheatre(theatre);
                    showing.setStartTime(LocalDateTime.now()
                            .plusDays(day)
                            .withHour(hour)
                            .withMinute(0)
                            .withSecond(0)
                            .withNano(0));
                    showings.add(showing);
                }
            }
        }

        showingRepository.saveAll(showings);
    }
}
