package org.example.kinobackend.controller;

import org.example.kinobackend.dto.ShowingResponse;
import org.example.kinobackend.model.Showing;
import org.example.kinobackend.service.ShowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showing")
public class ShowingRestController {
    private final ShowingService showingService;

    public ShowingRestController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping
    public ResponseEntity<List<ShowingResponse>> getAllShowings(){
        List<ShowingResponse> showingResponses = showingService.getAllShowings();
        return new ResponseEntity<>(showingResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowingResponse> getShowing(@PathVariable int id) {
        ShowingResponse showing = showingService.getShowingResponseById(id);
        return new ResponseEntity<>(showing, HttpStatus.OK);
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

    @GetMapping("/movie/upcomming/{movieId}")
    public List<ShowingResponse> getShowingsForMovie(@PathVariable int movieId) {
        return showingService.getUpcomingShowingsForMovie(movieId);
    }
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Showing>> getShowingsByMovieId(@PathVariable int movieId) {
        List<Showing> showingList = showingService.getShowingsByMovieId(movieId);
        return new ResponseEntity<>(showingList, HttpStatus.OK);

    }
}
