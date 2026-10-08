package org.example.kinobackend.controller;

import org.example.kinobackend.dto.TicketResponse;
import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.service.MovieService;
import org.example.kinobackend.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {
    private final TicketService ticketService;
    private final MovieService movieService;

    public TicketRestController(TicketService ticketService, MovieService movieService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
    }

    @GetMapping("{showingId}")
    public ResponseEntity<TicketResponse> getAllTicketsForMovie(@PathVariable int showingId) {
        Movie movieToBeFound = movieService.getMovieByShowingId(showingId);
        List<Ticket> ticketsToBeFound = ticketService.findTicketsByShowingId(showingId);
        return new ResponseEntity<>(new TicketResponse(movieToBeFound, ticketsToBeFound), HttpStatus.OK);
    }
}
