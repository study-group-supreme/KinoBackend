package org.example.kinobackend.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.repository.TheatreRepository;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

