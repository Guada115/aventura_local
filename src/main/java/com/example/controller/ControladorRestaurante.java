package com.example.controller;

import com.example.entidades.Restaurante;
import com.example.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class ControladorRestaurante {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public Restaurante createRestaurante(
            @RequestParam String nombre,
            @RequestParam String horario,
            @RequestParam String sector,
            @RequestParam String tipoComida,
            @RequestParam("foto") MultipartFile foto) throws IOException {

        Restaurante restaurante = new Restaurante(nombre, horario, sector, tipoComida, foto.getBytes());
        return restauranteRepository.save(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> updateRestaurante(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam String horario,
            @RequestParam String sector,
            @RequestParam String tipoComida,
            @RequestParam(required = false) MultipartFile foto) throws IOException {

        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        restaurante.setNombre(nombre);
        restaurante.setHorario(horario);
        restaurante.setSector(sector);
        restaurante.setTipoComida(tipoComida);

        if (foto != null && !foto.isEmpty()) {
            restaurante.setFoto(foto.getBytes());
        }

        Restaurante updatedRestaurante = restauranteRepository.save(restaurante);
        return ResponseEntity.ok(updatedRestaurante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        restauranteRepository.delete(restaurante);
        return ResponseEntity.noContent().build();
    }
}
