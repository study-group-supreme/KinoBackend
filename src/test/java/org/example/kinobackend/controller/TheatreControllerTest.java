package org.example.kinobackend.controller;

import org.example.kinobackend.model.Theatre;
import org.example.kinobackend.service.TheatreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TheatreRestController.class)
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

        mockMvc.perform(post("/api/theatre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.id").value(1));
        verify(theatreService).createTheatre(any(Theatre.class));
    }
//    @Test //Test virker så snart vi har et theatre i DB med id 1(Testet i postman)
//    public void putTheatreReturns200AndUpdatedTheatre() throws Exception {
//        Theatre updated = new Theatre();
//        updated.setId(1);
//        updated.setName("new");
//        when(theatreService.updateTheatre(anyInt(), any(Theatre.class))).thenReturn(updated);
//
//        mockMvc.perform(put("/theatres/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"new\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("new"));
//    }
}