package org.example.kinobackend.dto;

import java.time.LocalDateTime;

public record ShowingResponse(
        int id,
        LocalDateTime startTime,
        int movieId,
        String movieName,
        int theatreId,
        String theatreName
) {
}
