package com.example.controller;

import com.example.entidades.Transporte;
import com.example.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    @Autowired
    private TransporteRepository transporteRepository;

    // Obtener todos los transportes
    @GetMapping
    public List<Map<String, Object>> getAllTransportes() {
        List<Transporte> transportes = transporteRepository.findAll();
        return transportes.stream()
                .map(transporte -> {
                    Map<String, Object> transporteData = new HashMap<>();
                    transporteData.put("nombreTransporte", transporte.getNombreTransporte());
                    transporteData.put("tipoTransporte", transporte.getTipoTransporte());
                    transporteData.put("rutaTransporte", transporte.getRutaTransporte());
                    transporteData.put("horarioTransporte", transporte.getHorarioTransporte());
                    transporteData.put("imagenTransporte", transporte.getImagenBase64()); // Agregar imagen como Base64
                    return transporteData;
                })
                .collect(Collectors.toList());
    }

    // Obtener transporte por nombre
    @GetMapping("/{nombreTransporte}")
    public ResponseEntity<Transporte> getTransporteByNombre(@PathVariable String nombreTransporte) {
        Transporte transporte = transporteRepository.findById(nombreTransporte)
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
            byte[] imagenBytes = imagenTransporte.getBytes();
            Transporte transporte = new Transporte(nombreTransporte, tipoTransporte, rutaTransporte, horarioTransporte, imagenBytes);
            Transporte savedTransporte = transporteRepository.save(transporte);
            return ResponseEntity.ok(savedTransporte);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al guardar el transporte: " + e.getMessage()));
        }
    }

    // Actualizar un transporte existente (con posibilidad de actualizar la imagen)
    @PutMapping("/{nombreTransporte}")
    public ResponseEntity<Transporte> updateTransporte(
            @PathVariable String nombreTransporte,
            @RequestParam String tipoTransporte,
            @RequestParam String rutaTransporte,
            @RequestParam String horarioTransporte,
            @RequestParam(value = "imagenTransporte", required = false) MultipartFile imagenTransporte) {

        Transporte transporte = transporteRepository.findById(nombreTransporte)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));

        transporte.setTipoTransporte(tipoTransporte);
        transporte.setRutaTransporte(rutaTransporte);
        transporte.setHorarioTransporte(horarioTransporte);

        if (imagenTransporte != null && !imagenTransporte.isEmpty()) {
            try {
                byte[] imagenBytes = imagenTransporte.getBytes();
                transporte.setFotoTransporte(imagenBytes);
            } catch (Exception e) {
                return ResponseEntity.status(400).body(null);
            }
        }

        Transporte updatedTransporte = transporteRepository.save(transporte);
        return ResponseEntity.ok(updatedTransporte);
    }

    // Eliminar un transporte
    @DeleteMapping("/{nombreTransporte}")
    public ResponseEntity<Void> deleteTransporte(@PathVariable String nombreTransporte) {
        Transporte transporte = transporteRepository.findById(nombreTransporte)
                .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));
        transporteRepository.delete(transporte);
        return ResponseEntity.noContent().build();
    }
}
