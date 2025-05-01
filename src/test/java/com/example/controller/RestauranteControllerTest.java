package com.example.controller;

import com.example.entidades.Restaurante;
import com.example.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestauranteControllerTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private MultipartFile foto;

    @InjectMocks
    private ControladorRestaurante controladorRestaurante;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurante = new Restaurante("restaurante1", "12:00-22:00", "sector1", "comida1", new byte[]{1, 2, 3});
    }

    @Test
    void testCreateRestaurante() throws Exception {
        // Arrange
        when(foto.getBytes()).thenReturn(new byte[]{1, 2, 3});
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);

        // Act
        ResponseEntity<?> response = controladorRestaurante.createRestaurante(
                "restaurante1", "12:00-22:00", "sector1", "comida1", foto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    void testUpdateRestaurante() throws Exception {
        // Arrange
        when(restauranteRepository.findById("restaurante1")).thenReturn(java.util.Optional.of(restaurante));
        when(foto.getBytes()).thenReturn(new byte[]{4, 5, 6});
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);

        // Act
        ResponseEntity<?> response = controladorRestaurante.updateRestaurante(
                "restaurante1", "10:00-20:00", "sector2", "comida2", foto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    void testDeleteRestaurante() {
        // Arrange
        when(restauranteRepository.findById("restaurante1")).thenReturn(java.util.Optional.of(restaurante));

        // Act
        ResponseEntity<Void> response = controladorRestaurante.deleteRestaurante("restaurante1");

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(restauranteRepository, times(1)).delete(restaurante);
    }
}
