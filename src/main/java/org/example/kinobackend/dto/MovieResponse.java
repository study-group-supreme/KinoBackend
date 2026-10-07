package org.example.kinobackend.dto;

import org.example.kinobackend.model.Category;

import java.util.Set;

public record MovieResponse(
        int id,
        String name,
        int runtimeMinutes,
        String description,
        String posterUrl,
        int ageLimit,
        boolean isActive,
        Set<Category>categories
) {
}



