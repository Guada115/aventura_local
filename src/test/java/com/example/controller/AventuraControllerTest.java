package com.example.controller;

import com.example.entidades.Aventura;
import com.example.repository.AventuraRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AventuraController.class)
public class AventuraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AventuraRepository aventuraRepository;

    @Test
    void testGetAllAventuras() throws Exception {
        Aventura a1 = new Aventura();
        a1.setNombreAvent("Canopy");

        Aventura a2 = new Aventura();
        a2.setNombreAvent("Rafting");

        when(aventuraRepository.findAll()).thenReturn(List.of(a1, a2));

        mockMvc.perform(get("/api/aventuras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAventuraByNombre() throws Exception {
        Aventura aventura = new Aventura();
        aventura.setNombreAvent("Canopy");

        when(aventuraRepository.findById("Canopy")).thenReturn(Optional.of(aventura));

        mockMvc.perform(get("/api/aventuras/Canopy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreAvent").value("Canopy"));
    }
}
