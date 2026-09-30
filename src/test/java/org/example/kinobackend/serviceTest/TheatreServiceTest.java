package org.example.kinobackend.serviceTest;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TheatreServiceTest {
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private TheatreRepository theatreRepository;

    @Test
    public void createTheatreCreatesATheatre() {
        Theatre theatre = new Theatre();
        theatre.setName("test");
        theatreRepository.save(theatre);

        List<Theatre> theatreList = theatreRepository.findAll();
        assertTrue(theatreList.size() == 1);
    }

    @Test
    public void createTheatreThrowIllegalArgumentExceptionWhenNameIsBlank() {
        Theatre theatre = new Theatre();
        theatre.setName(" ");
        assertThrows(IllegalArgumentException.class, () -> theatreService.createTheatre(theatre));
    }

    @Test
    public void createTheatreThrowIllegalArgumentExceptionWhenNameIsNull() {
        Theatre theatre = new Theatre();
        theatre.setName(null);
        assertThrows(IllegalArgumentException.class, () -> theatreService.createTheatre(theatre));
    }
}

