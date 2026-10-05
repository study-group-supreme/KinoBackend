package org.example.kinobackend.config;

import org.example.kinobackend.model.Seat;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.SeatRepository;
import org.example.kinobackend.repository.TheatreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class InitDataTheaterAndSeat implements CommandLineRunner {

    private final TheatreRepository theatreRepository;
    private final SeatRepository seatRepository;

    public InitDataTheaterAndSeat(TheatreRepository theatreRepository, SeatRepository seatRepository) {
        this.theatreRepository = theatreRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Theatre smallTheatre = new Theatre();
        smallTheatre.setName("Theatre 1");
        theatreRepository.save(smallTheatre);

        Theatre largeTheatre = new Theatre();
        largeTheatre.setName("Theatre 2");
        theatreRepository.save(largeTheatre);

        createSeats(smallTheatre, 20, 12);
        createSeats(largeTheatre, 25, 16);
    }

    private void createSeats(Theatre theatre, int rows, int seatsPerRow) {
        List<Seat> seats = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            char rowLetter = (char) ('A' + row);

            for (int number = 1; number <= seatsPerRow; number++) {
                Seat seat = new Seat();
                seat.setSeatRow(rowLetter);
                seat.setSeatNumber(number);
                seat.setAvailable(true);
                seat.setTheatre(theatre);
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }
}
