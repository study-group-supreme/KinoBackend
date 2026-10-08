package org.example.kinobackend.dto;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Ticket;

import java.util.List;

public record TicketResponse(Movie movie, List<Ticket> tickets) {
}
