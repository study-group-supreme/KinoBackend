package org.example.kinobackend.controllerTest;

import org.example.kinobackend.controller.TheatreController;
import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TheatreController.class)
public class TheatreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TheatreService theatreService;

    @Test
    //201 == status code "created"
    public void postTheatreReturns201AndSavesTheatre() throws Exception {
        Theatre theatre = new Theatre();
        theatre.setName("test");
        theatre.setId(1);
        when(theatreService.createTheatre(any(Theatre.class))).thenReturn(theatre);

        mockMvc.perform(post("/theatres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.id").value(1));
    }
}