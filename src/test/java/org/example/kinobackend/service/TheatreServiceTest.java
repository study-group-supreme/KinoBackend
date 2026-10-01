package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TheatreServiceTest {
    @Mock
    private TheatreRepository theatreRepository;
    @InjectMocks
    private TheatreService theatreService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void createTheatreCreatesATheatre() {
        Theatre theatre = new Theatre();
        theatre.setName("test");
        when(theatreRepository.save(theatre)).thenReturn(theatre);
        Theatre result = theatreService.createTheatre(theatre);

        assertEquals(theatre, result);
        verify(theatreRepository).save(theatre);
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
    @Test
    public void updateTheatreUpdatesTheatreName() {
        Theatre existing = new Theatre();
        existing.setId(1);
        existing.setName("test");
        when(theatreRepository.findById(1)).thenReturn(Optional.of(existing));
        when(theatreRepository.save(existing)).thenReturn(existing);

        Theatre update = new Theatre();
        update.setName("newTest");

        Theatre result = theatreService.updateTheatre(1, update);

        assertEquals("newTest", result.getName());
        verify(theatreRepository).save(existing);
    }
    @Test
    public void updateTheatreThrowsIllegalArgumentExceptionWhenNameIsBlank(){
        Theatre theatre = new Theatre();
        theatre.setName("   ");
        assertThrows(IllegalArgumentException.class, () -> theatreService.updateTheatre(1, theatre));
        verify(theatreRepository, never()).save(any());
    }
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


    @Test
    void deleteTheatre_shouldDeleteWhenFound() {
        Theatre theatre = new Theatre();
        theatre.setId(1);

        when(theatreRepository.findById(1)).thenReturn(Optional.of(theatre));

        theatreService.deleteTheatre(1);

        verify(theatreRepository).findById(1);
        verify(theatreRepository).delete(theatre);
    }

    @Test
    void deleteTheatre_shouldThrowEntityNotFoundExceptionWhenNotFound() {
        when(theatreRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> theatreService.deleteTheatre(1));

        verify(theatreRepository).findById(1);
        verify(theatreRepository, never()).delete(any());
    }
}

