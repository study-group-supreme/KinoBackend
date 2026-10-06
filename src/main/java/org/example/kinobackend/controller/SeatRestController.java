package org.example.kinobackend.controller;

import org.example.kinobackend.dto.SeatResponse;
import org.example.kinobackend.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatRestController {
    private final SeatService seatService;

    public SeatRestController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/showing/{showingId}")
    public List<SeatResponse> getForSpecificSeats(@PathVariable int showingId) {
        return seatService.getSeatsForShowing(showingId);
    }
}