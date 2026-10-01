package org.example.kinobackend.service;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    TheatreService theatreService;

    @Test
    void getAllTheatresReturnsAllTheatres() {
        Theatre imax = new Theatre();
        Theatre regular = new Theatre();

        List<Theatre> allTheatres = new ArrayList<>();

        allTheatres.add(imax);
        allTheatres.add(regular);

        when(theatreRepository.findAll()).thenReturn(allTheatres);

        List<Theatre> result = theatreService.getAllTheatres();

        assertEquals(2, result.size());
        assertEquals(allTheatres, result);
    }




}