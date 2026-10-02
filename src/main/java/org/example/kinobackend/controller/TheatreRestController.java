package org.example.kinobackend.controller;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreRestController {
    private final TheatreService theatreService;

    public TheatreRestController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping("")
    public ResponseEntity<Theatre> postTheatre(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreService.createTheatre(theatre);

        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteTheatre(@PathVariable int id) {
        theatreService.deleteTheatre(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable int id, @RequestBody Theatre theatre) {
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatre);
        return new ResponseEntity<>(updatedTheatre, HttpStatus.OK);
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Theatre>> showAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }

    @GetMapping("/showSpecific/{id}")
    public ResponseEntity<Theatre> showSpecificTheatre(@PathVariable int id) {
        Theatre specificTheatre = theatreService.getTheatreById(id);
        return new ResponseEntity<>(specificTheatre, HttpStatus.OK);
    }
}


