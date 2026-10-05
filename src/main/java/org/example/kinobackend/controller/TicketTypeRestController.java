package org.example.kinobackend.controller;


import org.example.kinobackend.model.TicketType;
import org.example.kinobackend.service.TicketTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticketType")
public class TicketTypeRestController {

    private final TicketTypeService ticketTypeService;

    public TicketTypeRestController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping("")
    public List<TicketType> showTicketTypes() {
        return ticketTypeService.getAll();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TicketType> getTicketTypeByName(@PathVariable String name) {
        return ticketTypeService.findTicketTypeByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketType> getTicketTypeById(@PathVariable int id) {
        return ticketTypeService.findTicketTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<TicketType> createTicketType(@RequestBody TicketType ticketType) {
        TicketType savedTicketType = ticketTypeService.createTicketType(ticketType);

        return ResponseEntity.ok(savedTicketType);
    }
}