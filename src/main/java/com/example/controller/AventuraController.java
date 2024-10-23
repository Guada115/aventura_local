package com.example.controller;

import com.example.entidades.Aventura;
import com.example.repository.AventuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/aventuras")
public class AventuraController {

    @Autowired
    private AventuraRepository aventuraRepository;

    @GetMapping
    public List<Aventura> getAllAventuras() {
        return aventuraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aventura> getAventuraById(@PathVariable Long id) {
        Aventura aventura = aventuraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));
        return ResponseEntity.ok(aventura);
    }


    @PostMapping
    public ResponseEntity<?> createAventura(
            @RequestParam String nombreAvent,
            @RequestParam String tipoAvent,
            @RequestParam String zonaAvent,
            @RequestParam String horarioAvent,
            @RequestParam("fotoAvent") MultipartFile fotoAvent) {
        try {
            Aventura aventura = new Aventura(nombreAvent, tipoAvent, zonaAvent, horarioAvent, fotoAvent.getBytes());
            Aventura savedAventura = aventuraRepository.save(aventura);
            return ResponseEntity.ok(savedAventura);
        } catch (IOException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al procesar la imagen: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", "Error al guardar la aventura: " + e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Aventura> updateAventura(
            @PathVariable Long id,
            @RequestParam String nombreAvent,
            @RequestParam String tipoAvent,
            @RequestParam String zonaAvent,
            @RequestParam String horarioAvent,
            @RequestParam(required = false) MultipartFile fotoAvent) throws IOException {

        Aventura aventura = aventuraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));

        aventura.setNombreAvent(nombreAvent);
        aventura.setTipoAvent(tipoAvent);
        aventura.setZonaAvent(zonaAvent);
        aventura.setHorarioAvent(horarioAvent);

        if (fotoAvent != null && !fotoAvent.isEmpty()) {
            aventura.setFotoAvent(fotoAvent.getBytes());
        }

        Aventura updatedAventura = aventuraRepository.save(aventura);
        return ResponseEntity.ok(updatedAventura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAventura(@PathVariable Long id) {
        Aventura aventura = aventuraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));
        aventuraRepository.delete(aventura);
        return ResponseEntity.noContent().build();
    }
}
