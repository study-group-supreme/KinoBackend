package org.example.kinobackend.dto;

import java.util.List;

public record ShowingByDateResponse(String date, List<ShowingResponse> showings) {
}
