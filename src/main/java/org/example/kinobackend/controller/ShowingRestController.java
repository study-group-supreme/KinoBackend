package org.example.kinobackend.controller;

import org.example.kinobackend.model.Showing;
import org.example.kinobackend.service.ShowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showing")
public class ShowingRestController {
    private ShowingService showingService;

    public ShowingRestController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @PostMapping("")
    public ResponseEntity<Showing> createShowing(@RequestBody Showing showing) {
        Showing newShowing = showingService.createShowing(showing);
        return new ResponseEntity<>(newShowing, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteShowing(@PathVariable int id) {
        showingService.deleteShowing(id);

    }
}
