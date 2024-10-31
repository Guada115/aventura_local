package com.example.controller;

import com.example.entidades.Restaurante;
import com.example.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurantes")
public class ControladorRestaurante {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // Obtener todos los restaurantes
    @GetMapping
    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    // Obtener un restaurante por su nombre (identificador)
    @GetMapping("/{nombre}")
    public ResponseEntity<Restaurante> getRestauranteByNombre(@PathVariable String nombre) {
        Restaurante restaurante = restauranteRepository.findById(nombre)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return ResponseEntity.ok(restaurante);
    }

    // Crear un nuevo restaurante
    @PostMapping
    public ResponseEntity<?> createRestaurante(
            @RequestParam String nombre,
            @RequestParam String horario,
            @RequestParam String sector,
            @RequestParam String tipoComida,
            @RequestParam("foto") MultipartFile foto) {
        try {
            Restaurante restaurante = new Restaurante(nombre, horario, sector, tipoComida, foto.getBytes());
            Restaurante savedRestaurante = restauranteRepository.save(restaurante);
            return ResponseEntity.ok(savedRestaurante);
        } catch (IOException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al procesar la imagen: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al guardar el restaurante: " + e.getMessage()));
        }
    }

    // Actualizar un restaurante existente
    @PutMapping("/{nombre}")
    public ResponseEntity<?> updateRestaurante(
            @PathVariable String nombre,
            @RequestParam String horario,
            @RequestParam String sector,
            @RequestParam String tipoComida,
            @RequestParam(required = false) MultipartFile foto) {
        try {
            Restaurante restaurante = restauranteRepository.findById(nombre)
                    .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

            restaurante.setHorario(horario);
            restaurante.setSector(sector);
            restaurante.setTipoComida(tipoComida);

            if (foto != null && !foto.isEmpty()) {
                restaurante.setFoto(foto.getBytes());
            }

            Restaurante updatedRestaurante = restauranteRepository.save(restaurante);
            return ResponseEntity.ok(updatedRestaurante);
        } catch (IOException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al procesar la imagen: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al actualizar el restaurante: " + e.getMessage()));
        }
    }

    // Eliminar un restaurante existente
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable String nombre) {
        Restaurante restaurante = restauranteRepository.findById(nombre)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        restauranteRepository.delete(restaurante);
        return ResponseEntity.noContent().build();
    }
}
