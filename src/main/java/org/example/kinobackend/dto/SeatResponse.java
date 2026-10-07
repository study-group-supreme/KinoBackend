package org.example.kinobackend.dto;

public record SeatResponse(int id, char row, int number, boolean taken) {
}