package org.example.kinobackend.serviceTest;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        theatreRepository.deleteAll();
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
}

