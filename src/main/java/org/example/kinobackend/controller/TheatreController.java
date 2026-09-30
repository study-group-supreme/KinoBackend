package org.example.kinobackend.controller;

import org.example.kinobackend.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TheatreController {

    @Autowired
    private TheatreService theatreService;


}
