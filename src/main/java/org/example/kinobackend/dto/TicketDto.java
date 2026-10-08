package org.example.kinobackend.dto;

public record TicketDto(int ticketId, char seatRow, int seatNum, String ticketType, double price) {
}
