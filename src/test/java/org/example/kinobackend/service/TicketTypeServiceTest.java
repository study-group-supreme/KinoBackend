package org.example.kinobackend.service;

import org.example.kinobackend.model.TicketType;
import org.example.kinobackend.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
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
    void updateTicketType_shouldUpdateAndReturnTicketType() {
        int id = 1;

        TicketType existing = new TicketType();
        existing.setName("Old Name");
        existing.setPrice(50);

        TicketType update = new TicketType();
        update.setName("New Name");
        update.setPrice(75);

        TicketType saved = new TicketType();
        saved.setName("New Name");
        saved.setPrice(75);

        when(ticketTypeRepository.findByName("New Name"))
                .thenReturn(Optional.empty());

        when(ticketTypeRepository.findById(id))
                .thenReturn(Optional.of(existing));

        when(ticketTypeRepository.save(existing))
                .thenReturn(saved);

        Optional<TicketType> result =
                ticketTypeService.updateTicketType(id, update);

        assertTrue(result.isPresent());
        assertEquals("New Name", result.get().getName());
        assertEquals(75, result.get().getPrice());

        verify(ticketTypeRepository).findByName("New Name");
        verify(ticketTypeRepository).findById(id);
        verify(ticketTypeRepository).save(existing);
    }

    @Test
    void updateTicketType_shouldThrow_WhenNameIsNull() {
        TicketType ticketType = new TicketType();
        ticketType.setName(null);
        ticketType.setPrice(50);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.updateTicketType(1, ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verifyNoInteractions(ticketTypeRepository);
    }

    @Test
    void updateTicketType_shouldThrow_WhenNameIsBlank() {
        TicketType ticketType = new TicketType();
        ticketType.setName("   ");
        ticketType.setPrice(50);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.updateTicketType(1, ticketType)
        );

        assertEquals("Ticket type must be named", exception.getMessage());

        verifyNoInteractions(ticketTypeRepository);
    }

    @Test
    void updateTicketType_shouldThrow_WhenPriceIsZero() {
        TicketType ticketType = new TicketType();
        ticketType.setName("VIP");
        ticketType.setPrice(0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.updateTicketType(1, ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verifyNoInteractions(ticketTypeRepository);
    }

    @Test
    void updateTicketType_shouldThrow_WhenPriceIsNegative() {
        TicketType ticketType = new TicketType();
        ticketType.setName("VIP");
        ticketType.setPrice(-10);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.updateTicketType(1, ticketType)
        );

        assertEquals("Price must be greater than 0", exception.getMessage());

        verifyNoInteractions(ticketTypeRepository);
    }

    @Test
    void updateTicketType_shouldThrow_WhenName_AlreadyExists() {
        int id = 1;

        TicketType update = new TicketType();
        update.setName("VIP");
        update.setPrice(100);

        TicketType existingWithSameName = new TicketType();
        existingWithSameName.setName("VIP");
        existingWithSameName.setPrice(100);

        when(ticketTypeRepository.findByName("VIP"))
                .thenReturn(Optional.of(existingWithSameName));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticketTypeService.updateTicketType(id, update)
        );

        assertEquals(
                "Ticket type with that name already exists",
                exception.getMessage()
        );

        verify(ticketTypeRepository).findByName("VIP");
        verify(ticketTypeRepository, never()).findById(anyInt());
        verify(ticketTypeRepository, never()).save(any());
    }

    @Test
    void updateTicketType_shouldThrow_WhenTicketType_DoesNotExist() {
        int id = 1;

        TicketType update = new TicketType();
        update.setName("VIP");
        update.setPrice(100);

        when(ticketTypeRepository.findByName("VIP"))
                .thenReturn(Optional.empty());

        when(ticketTypeRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> ticketTypeService.updateTicketType(id, update)
        );

        verify(ticketTypeRepository).findByName("VIP");
        verify(ticketTypeRepository).findById(id);
        verify(ticketTypeRepository, never()).save(any());
    }
}