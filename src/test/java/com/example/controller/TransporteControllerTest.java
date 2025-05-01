package com.example.controller;

import com.example.entidades.Transporte;
import com.example.repository.TransporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransporteControllerTest {

    @Mock
    private TransporteRepository transporteRepository;

    @Mock
    private MultipartFile imagenTransporte;

    @InjectMocks
    private TransporteController transporteController;

    private Transporte transporte;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transporte = new Transporte("bus1", "urbano", "ruta1", "08:00", new byte[]{1, 2, 3});
    }

    @Test
    void testCreateTransporte() throws Exception {
        // Arrange
        when(imagenTransporte.getBytes()).thenReturn(new byte[]{1, 2, 3});
        when(transporteRepository.save(any(Transporte.class))).thenReturn(transporte);

        // Act
        ResponseEntity<?> response = transporteController.createTransporte(
                "bus1", "urbano", "ruta1", "08:00", imagenTransporte);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(transporteRepository, times(1)).save(any(Transporte.class));
    }

    @Test
    void testUpdateTransporte() throws Exception {
        // Arrange
        when(transporteRepository.findById("bus1")).thenReturn(java.util.Optional.of(transporte));
        when(imagenTransporte.getBytes()).thenReturn(new byte[]{4, 5, 6});
        when(transporteRepository.save(any(Transporte.class))).thenReturn(transporte);

        // Act
        ResponseEntity<?> response = transporteController.updateTransporte(
                "bus1", "urbano", "ruta2", "09:00", imagenTransporte);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(transporteRepository, times(1)).save(any(Transporte.class));
    }

    @Test
    void testDeleteTransporte() {
        // Arrange
        when(transporteRepository.findById("bus1")).thenReturn(java.util.Optional.of(transporte));

        // Act
        ResponseEntity<Void> response = transporteController.deleteTransporte("bus1");

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(transporteRepository, times(1)).delete(transporte);
    }
}
