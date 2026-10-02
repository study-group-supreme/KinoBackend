package org.example.kinobackend.service;

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
    void createTicketTypeShouldCreateTicketType() {
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
    void createTicketTypeShouldThrowExceptionWhenNameIsNull() {
        ticketType.setName(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicketTypeShouldThrowExceptionWhenNameIsBlank() {
        ticketType.setName("   ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicketTypeShouldThrowExceptionWhenPriceIsZero() {
        ticketType.setPrice(0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicketTypeShouldThrowExceptionWhenPriceIsNegative() {
        ticketType.setPrice(-10);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.createTicketType(ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void createTicketTypeShouldThrowExceptionWhenNameAlreadyExists() {
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
}