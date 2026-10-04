package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.TicketType;
import org.example.kinobackend.repository.TicketTypeRepository;
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
class TicketTypeServiceTest {

    @Mock
    private TicketTypeRepository ticketTypeRepository;

    @InjectMocks
    private TicketTypeService ticketTypeService;

    private TicketType ticketType;

    @BeforeEach
    void setUp() {
        ticketType = new TicketType();
        ticketType.setName("Adult");
        ticketType.setPrice(120.0);
    }

    @Test
    void createTicketType_ShouldCreateTicketType() {
        when(ticketTypeRepository.findByName("Adult"))
                .thenReturn(Optional.empty());

        when(ticketTypeRepository.save(ticketType))
                .thenReturn(ticketType);

        TicketType result = ticketTypeService.createTicketType(ticketType);

        assertNotNull(result);
        assertEquals("Adult", result.getName());
        assertEquals(120.0, result.getPrice());

        verify(ticketTypeRepository).findByName("Adult");
        verify(ticketTypeRepository).save(ticketType);
    }

    @Test
    void createTicketType_ShouldThrowException_WhenNameIsNull() {
        ticketType.setName(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicket_TypeShouldThrowException_WhenNameIsBlank() {
        ticketType.setName("   ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicket_TypeShouldThrowException_WhenPriceIsZero() {
        ticketType.setPrice(0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicket_TypeShouldThrowException_WhenPriceIsNegative() {
        ticketType.setPrice(-10);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicket_TypeShouldThrowException_WhenNameAlreadyExists() {
        TicketType existingTicketType = new TicketType();
        existingTicketType.setName("Adult");
        existingTicketType.setPrice(120.0);

        when(ticketTypeRepository.findByName("Adult"))
                .thenReturn(Optional.of(existingTicketType));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals(
                "Ticket type with that name already exists",
                exception.getMessage()
        );

        verify(ticketTypeRepository).findByName("Adult");
        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void deleteTicketTypeById_shouldDeleteTicketType_WhenItExists() {
        int id = 1;
        TicketType ticketType = new TicketType();
        ticketType.setId(id);

        when(ticketTypeRepository.findById(id))
                .thenReturn(Optional.of(ticketType));

        ticketTypeService.deleteTicketTypeById(id);

        verify(ticketTypeRepository).findById(id);
        verify(ticketTypeRepository).delete(ticketType);
    }

    @Test
    void deleteTicketTypeById_shouldThrowException_WhenTicketTypeDoesNotExist() {
        int id = 1;

        when(ticketTypeRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> ticketTypeService.deleteTicketTypeById(id)
        );

        verify(ticketTypeRepository).findById(id);
        verify(ticketTypeRepository, never()).delete(any());
    }
}