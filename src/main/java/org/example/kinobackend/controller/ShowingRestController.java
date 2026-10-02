package org.example.kinobackend.controller;

import org.example.kinobackend.service.ShowingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/showing")
public class ShowingRestController {
    private ShowingService showingService;

    public ShowingRestController(ShowingService showingService) {
        this.showingService = showingService;
    }
}
