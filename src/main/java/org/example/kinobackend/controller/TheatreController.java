package org.example.kinobackend.controller;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}


