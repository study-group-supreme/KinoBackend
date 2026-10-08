package org.example.kinobackend.dto;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.model.Seat;
import org.example.kinobackend.model.Ticket;
import org.example.kinobackend.model.TicketType;

import java.util.List;

public record TicketResponse(Movie movie, List<TicketDto> tickets) {
}
