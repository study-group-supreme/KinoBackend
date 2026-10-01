package org.example.kinobackend.controller;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
    private TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
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
}


