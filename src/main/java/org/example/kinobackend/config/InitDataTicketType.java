package org.example.kinobackend.config;

import org.example.kinobackend.model.TicketType;
import org.example.kinobackend.repository.TicketTypeRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitDataTicketType implements CommandLineRunner {

    @Autowired
    TicketTypeRepository ticketTypeRepository;


    @Override
    public void run(String @NonNull ... args) throws Exception {
        if (ticketTypeRepository.count() > 0) {
            return;
        }

        try {
            TicketType adult = new TicketType();
            adult.setName("Adult");
            adult.setPrice(120.0);

            TicketType child = new TicketType();
            child.setName("Child");
            child.setPrice(80.0);

            TicketType senior = new TicketType();
            senior.setName("Senior");
            senior.setPrice(100.0);

            ticketTypeRepository.save(adult);
            ticketTypeRepository.save(child);
            ticketTypeRepository.save(senior);
        } catch (Exception e) {
            System.err.println("Failed to seed ticket types: " + e.getMessage());
        }
    }
}
