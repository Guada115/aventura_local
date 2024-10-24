package com.example.controller;

import com.example.entidades.Transporte;
import com.example.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    @Autowired
    private TransporteRepository transporteRepository;

    // Obtener todos los transportes
    @GetMapping
    public List<Transporte> getAllTransportes() {
        return transporteRepository.findAll();
    }

    // Obtener transporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transporte> getTransporteById(@PathVariable Long id) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));
        return ResponseEntity.ok(transporte);
    }

    // Crear un nuevo transporte
    @PostMapping
    public ResponseEntity<?> createTransporte(
            @RequestParam String nombreTransporte,
            @RequestParam String tipoTransporte,
            @RequestParam String rutaTransporte,
            @RequestParam String horarioTransporte) {
        try {
            Transporte transporte = new Transporte(nombreTransporte, tipoTransporte, rutaTransporte, horarioTransporte);
            Transporte savedTransporte = transporteRepository.save(transporte);
            return ResponseEntity.ok(savedTransporte);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al guardar el transporte: " + e.getMessage()));
        }
    }

    // Actualizar un transporte existente
    @PutMapping("/{id}")
    public ResponseEntity<Transporte> updateTransporte(
            @PathVariable Long id,
            @RequestParam String nombreTransporte,
            @RequestParam String tipoTransporte,
            @RequestParam String rutaTransporte,
            @RequestParam String horarioTransporte) {

        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));

        transporte.setNombreTransporte(nombreTransporte);
        transporte.setTipoTransporte(tipoTransporte);
        transporte.setRutaTransporte(rutaTransporte);
        transporte.setHorarioTransporte(horarioTransporte);

        Transporte updatedTransporte = transporteRepository.save(transporte);
        return ResponseEntity.ok(updatedTransporte);
    }

    // Eliminar un transporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransporte(@PathVariable Long id) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));
        transporteRepository.delete(transporte);
        return ResponseEntity.noContent().build();
    }
}
