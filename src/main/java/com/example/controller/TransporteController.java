package com.example.controller;

import com.example.entidades.Transporte;
import com.example.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // Crear un nuevo transporte con imagen
    @PostMapping
    public ResponseEntity<?> createTransporte(
            @RequestParam String nombreTransporte,
            @RequestParam String tipoTransporte,
            @RequestParam String rutaTransporte,
            @RequestParam String horarioTransporte,
            @RequestParam("imagenTransporte") MultipartFile imagenTransporte) {
        try {
            // Convertir la imagen a un arreglo de bytes
            byte[] imagenBytes = imagenTransporte.getBytes();

            // Crear el objeto transporte
            Transporte transporte = new Transporte(nombreTransporte, tipoTransporte, rutaTransporte, horarioTransporte, imagenBytes);

            // Guardar en la base de datos
            Transporte savedTransporte = transporteRepository.save(transporte);
            return ResponseEntity.ok(savedTransporte);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al guardar el transporte: " + e.getMessage()));
        }
    }

    // Actualizar un transporte existente (con posibilidad de actualizar la imagen)
    @PutMapping("/{id}")
    public ResponseEntity<Transporte> updateTransporte(
            @PathVariable Long id,
            @RequestParam String nombreTransporte,
            @RequestParam String tipoTransporte,
            @RequestParam String rutaTransporte,
            @RequestParam String horarioTransporte,
            @RequestParam(value = "imagenTransporte", required = false) MultipartFile imagenTransporte) {

        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));

        // Actualizar campos de texto
        transporte.setNombreTransporte(nombreTransporte);
        transporte.setTipoTransporte(tipoTransporte);
        transporte.setRutaTransporte(rutaTransporte);
        transporte.setHorarioTransporte(horarioTransporte);

        // Si se proporciona una nueva imagen, actualizarla
        if (imagenTransporte != null && !imagenTransporte.isEmpty()) {
            try {
                byte[] imagenBytes = imagenTransporte.getBytes();
                transporte.setImagenTransporte(imagenBytes);
            } catch (Exception e) {
                return ResponseEntity.status(400).body(null);
            }
        }

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
